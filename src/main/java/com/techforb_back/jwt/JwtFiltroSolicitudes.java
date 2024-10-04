package com.techforb_back.jwt;

import com.techforb_back.servicio.UserDetailImplementacion;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFiltroSolicitudes extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtFiltroSolicitudes.class);

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailImplementacion userDetailsImplementacion;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.equals("/autenticacion/login") || path.equals("/autenticacion/register") || path.equals("/autenticacion/validarToken")) {
            filterChain.doFilter(request, response); 
            return;
        }

        try {
            String token = getToken(request);
            if (token != null && jwtProvider.validarToken(token)) {
                String nombreUsuario = jwtProvider.getNombreUSuarioFromToken(token);
                UserDetails userDetails = userDetailsImplementacion.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("Fallo el metodo doFilterInternal");
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {    //extraigo el token de la request y le borro la parte de berear
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }

}
