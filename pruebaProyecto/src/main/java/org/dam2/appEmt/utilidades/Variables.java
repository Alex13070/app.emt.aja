package org.dam2.appEmt.utilidades;

import java.time.LocalDateTime;

public class Variables {

    /*
	 *	Key para poder hacer consultas a los controladores de la emt.
	 */
    public static String emtKey = "";
    
    /**
     * Fecha refresco token emt
     */
    public static LocalDateTime fechaRefrescoTokenEmt = LocalDateTime.MIN;

    /**
     * Fecha refresco archivo de paradas
     */
    public static LocalDateTime fechaRefrescoParadas = LocalDateTime.MIN;
}
