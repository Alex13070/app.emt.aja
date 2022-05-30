package org.dam2.appEmt.configuration.logs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Hilo que se lanzara para escribir logs
 */
@Data
@AllArgsConstructor
public class Logs implements Runnable {

    /**
     * Fecha y hora exactas de la generacion
     */
    private LocalDateTime time;

    /**
     * Controlador de la peticion
     */
    private String controller;

    /**
     * Mensaje
     */
    private String msg;
    

    /**
     * Cuerpo a ejecutar por el hilo
     */
    @Override
    public void run() {
        escribirLog();       
    }
    
    /**
     * Se encarga de escribir los datos en un fichero
     */
    private void escribirLog () {
        //TODO: Bloquear archivo lock

        //TODO: Escribir en fichero 
        System.out.println(time.toString() + " " + controller + " -- " + msg);

        //TODO: Desbloquear archivo lock
    }
    
}
