package com.example.demo.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.jwt")
@NoArgsConstructor
@Data
public class JwtConfig {

  private String secretKey;
  private String tokenPrefix;
  private Integer tokenExpirationAfterDays;

  public String getAuthorizationHeader() {
    return HttpHeaders.AUTHORIZATION;
  }

}

