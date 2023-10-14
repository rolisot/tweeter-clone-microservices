package com.lisot.gateway.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtService {

    public static final String SECRET = "24671eaa0d473bbae152cda28f3b19ce9b45c0d722150eb24f9dfd06cf5d815d";

    public void validateToken(final String token){
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey(){
        byte[] keyDecoded = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyDecoded);
    }
}
