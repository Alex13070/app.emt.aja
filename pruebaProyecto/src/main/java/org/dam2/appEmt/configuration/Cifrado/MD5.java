package org.dam2.appEmt.configuration.Cifrado;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase en la que se almacenan las funciones de encriptacion de las password
 */
public class MD5 {

    /**
     * Encripta la password
     * @param input password
     * @return password encriptada
     */
    public static String encriptar(String input) {

        String hashtext = null;

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error en el encriptado de la password");
        }

        return hashtext;
    }
}