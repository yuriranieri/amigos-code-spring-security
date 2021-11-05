package com.example.demo.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {
//  will invoke the filter once per request
//  the client will send a token for every single request and
//  then the server validate whether the token still valid

  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String authorizationHeaer = request.getHeader(jwtConfig.getAuthorizationHeader());
    // get the token from the header

    if (Strings.isNullOrEmpty(authorizationHeaer) || !authorizationHeaer.startsWith(
        jwtConfig.getTokenPrefix())) {
      filterChain.doFilter(request, response);
      return; // we reject the request if the token is empty/null or does not start with Bearer
    }

    String token = authorizationHeaer.replace(jwtConfig.getTokenPrefix(), "");

    try {
      Jws<Claims> claimsJws = Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token); // A signed JWT is called a 'JWS'

//    The key from before is being used to validate the signature of the JWT.
//    If it fails to verify the JWT, a SignatureException (which extends from JwtException) is thrown

      Claims body = claimsJws.getBody(); // payload
      String username = body.getSubject();
      var authorities = (List<Map<String, String>>) body.get("authorities");

      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
          .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
          .collect(Collectors.toSet());

      Authentication authentication = new UsernamePasswordAuthenticationToken(
          username, null, simpleGrantedAuthorities);

      SecurityContextHolder.getContext().setAuthentication(authentication); // is authenticated
    } catch (JwtException e) {
      throw new IllegalStateException(String.format("Token %s cannot be truest", token));
    }

    filterChain.doFilter(request, response);
//  pass the request and response that we got in this filter for the next one
  }
}
