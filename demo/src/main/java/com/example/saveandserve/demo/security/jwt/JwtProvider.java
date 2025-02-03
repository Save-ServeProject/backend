package com.example.saveandserve.demo.security.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.saveandserve.demo.entity.Usuario;
import com.example.saveandserve.demo.entity.UsuarioRol;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;

@Log
@Component
public class JwtProvider {
	
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT ";
	
    // Se recomienda que la clave secreta sea larga y aleatoria
    private String jwtSecreto = "IESDoctorBalmis-2DAW-DesarrolloWebEntornoServidor";
	
    private int jwtDurationTokenEnSegundos = 864000; // Por ejemplo, 10 días
	
    public String generateToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Date tokenExpirationDate = new Date(System.currentTimeMillis() + jwtDurationTokenEnSegundos * 1000);

        return Jwts.builder()
                .header().add("typ", TOKEN_TYPE).and()
                .subject(usuario.getEmail())  // Se usa email en lugar de ID o username
                .issuedAt(new Date())
                .expiration(tokenExpirationDate)
                .claim("fullname", usuario.getFullName())
                .claim("roles", usuario.getRoles().stream()
                        .map(UsuarioRol::name)
                        .collect(Collectors.joining(", ")))
                .signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .compact();
    }

    public String getUserEmailFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        // Se usa email
        return claims.getSubject(); 
    }
	
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .build()
                .parseSignedClaims(authToken);
            return true;
        } catch (SecurityException ex) {
            log.info("Error en la firma del token JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.info("Token malformado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("El token ha expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims vacío");
        }
        return false;
    }
}
