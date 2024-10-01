package com.techforb_back.jwt;

import com.techforb_back.servicio.UserDetailImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private final SecretKey secret_key;

    public JwtProvider() {
        // Generar una clave segura para HS256
        this.secret_key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generarToken(UserDetails userDetails) {   //cuando la use, pasar como argumento UserDetailImplementacion.loadByUserName(email) por que eso me devuelve un UserDetails

        UserDetailImpl userDetailImpl = (UserDetailImpl) userDetails;  //hago el casteo transformado el parametro a mi implementacion de UserDetails para poder acceder a los atributos

        return Jwts.builder()
                .setSubject(userDetailImpl.getUsername())
                .claim("nombre", userDetailImpl.getNombre())
                .claim("apellido", userDetailImpl.getApellido())
                .claim("nacimiento", userDetailImpl.getNacimiento())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();

    }

    public String getNombreUSuarioFromToken(String token) {
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("Token vacio");
        } catch (SignatureException e) {
            logger.error("Firma no válida");
        }
        return false;

    }
}
