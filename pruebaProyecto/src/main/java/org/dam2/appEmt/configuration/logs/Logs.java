package org.dam2.appEmt.configuration.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Hilo que se lanzara para escribir logs
 */
@Slf4j
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
     * Semaforo que utilizaremos para manejar la concurrencia a la hora de escribir los logs
     */
    private static Semaphore sem = new Semaphore(1);

    /**
     * Cuerpo a ejecutar por el hilo
     */
    @Override
    public void run() {
        try {
            sem.acquire();
            escribirLog();
            sem.release();
        } catch (Exception e) {
            log.error("Error", "Error de concurrencia al escribir log {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Se encarga de escribir los datos en un fichero
     */
    private void escribirLog() {
        try (
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(
                    
                    //He decidido no poner carpeta para guardarlos.
                    new File(LocalDate.now().toString() + ".txt"), true
                )
            )
        ){

            bw.append(time.toString() + " " + controller + " -- " + msg + "\n");
            
        } catch (Exception e) {
            log.error("Error", "Error al escribir el log {}", e.getMessage());
            e.printStackTrace();
        }
    }

}
