package org.dam2.appEmt.configuration.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.dam2.appEmt.controladoresEmt.FuncionesEmt;
import org.dam2.appEmt.utilidades.Variables;
import org.springframework.stereotype.Component;

/**
 * Filtro a las peticiones de la EMT
 */
@Component
public class EmtAuthenticationFilter implements Filter {

    /**
     * Dias que tardara el servidor en renovar la lista de paradas.
     */
    private static final long DIAS_RENOVAR_PARADAS = 7;

    /**
     * Filtro
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if (LocalDateTime.now().isAfter(Variables.fechaRefrescoTokenEmt)) {

            if (!FuncionesEmt.login()) {
                throw new RuntimeException("No se ha podido hacer login en la emt");
            }

            Variables.fechaRefrescoTokenEmt = LocalDateTime.now().plusDays(1);

        }

        if (LocalDateTime.now().isAfter(Variables.fechaRefrescoParadas)) {

            if (!FuncionesEmt.recogerParadas()) {
                throw new RuntimeException("No se ha podido guardar los archivos de parada");
            }

            Variables.fechaRefrescoParadas = LocalDateTime.now().plusDays(DIAS_RENOVAR_PARADAS);

        }

        chain.doFilter(request, response);
        
    }
    
    
}
