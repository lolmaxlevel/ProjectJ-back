package com.lolmaxlevel.backend_j.security.jwt;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class JwtProperties {
    private String secret;
    private String refreshSecret;
}
