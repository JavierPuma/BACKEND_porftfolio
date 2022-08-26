package com.pumajavier.portfolio.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pumajavier.portfolio.security.service.UserDetailsImpl;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${portfolio.jwtSecret}")
    private String jwtSecret;
    @Value("${portfolio.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(new Date((new Date().getTime() + jwtExpirationMs))).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("La firma del JWT no es válida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("El token JWT no es válido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("El token JWT expiró: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("El token JWT no es soportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Los claims del JWT están vacios: {}", e.getMessage());
        }
        return false;
    }
}