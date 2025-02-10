package com.edmarzungo.pedidosnamao.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {
//
//    private long jwtExpiration = 123456;
//    private String secretKey = "01e462bb864f17621931ee2685401fad35a9e308518a5550b7fafde3aace957777158bae9b32ec03ac2b2e303794277e0628c7b06510204ff801f7b2317559b2719ee56f901f5edf978b77a28feb60333ff6a40790878edab04f890369e8d737b6536d882b9703a16aa964770c15ccc310831e60af1fc110930204740ee71687c4e6f407e74ba2733c6706d5b72462f986c8d3acb25dee2a1231002235141c7e1f1d735913581cc2c44806ebeb8bee3e93ecb0c5eeee165435c5576a6dc1562566f0df55529595c680564ed66a67a26aeeb081a68ea18b6b5c1704b9c944b54a6b63e75e066e209dca4ca2e0b1ee5ee1eb85836d85eb4f8cde4f75a7a47070de";
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolve) {
//        final Claims claims = extractClaims(token);
//        return claimsResolve.apply(claims);
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInkey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public String generateToken(UserDetails userDetails){
//        return generateToken(new HashMap<>(), userDetails);
//    }
//
//    public String generateToken(HashMap<String,Object> claims, UserDetails userDetails) {
//        return buildToken(claims, userDetails, jwtExpiration);
//    }
//
//    private String buildToken(HashMap<String, Object> claims, UserDetails userDetails, long jwtExpiration) {
//        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
//                .claim("authorities", authorities)
//                .signWith(getSignInkey())
//                .compact();
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails){
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private Key getSignInkey() {
//        byte[] keyByte = Decoders.BASE64URL.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyByte);
//    }

}
