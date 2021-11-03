package com.example.demo.jwt;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtSecretKey {

  private final JwtConfig jwtConfig;

  @Bean
  public SecretKey secretKey() {
    return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
  }

}
