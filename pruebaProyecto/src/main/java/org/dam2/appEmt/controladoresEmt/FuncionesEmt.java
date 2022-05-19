package org.dam2.appEmt.controladoresEmt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dam2.appEmt.utilidades.Constantes;
import org.dam2.appEmt.utilidades.Passwords;
import org.dam2.appEmt.utilidades.Variables;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FuncionesEmt {

    /**
	 * Se encarga de abrir sesion en los servidores de la EMT.
	 * @return {@true exito de la operacion}
     *          {@false fracaso de la operacion}
	 */
	public static boolean login() {
	
		RestTemplate restTemplate = new RestTemplate();

		boolean exito;
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-ClientId", Passwords.CLIENT_ID);
			headers.set("passKey", Passwords.PASS_KEY);
			
			//headers.forEach((k,v) ->  System.out.println(k + " " + v));
			
			HttpEntity<String> request = new HttpEntity<String>("headrers", headers);
			//HttpHeaders h = request.getHeaders();
			ResponseEntity<String> response = restTemplate.exchange(Constantes.URL_LOGIN, HttpMethod.GET, request, String.class);
		
			
			//h.forEach((k,v) ->  System.out.println("h:" + k + " " + v));
			
			String s = response.getBody();
			
            //System.out.println(s);

			Variables.emtKey = sacarToken(s);

			exito = true;			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			exito = false;
		}

		return exito;
	}

	/**
	 * Saca el token del json devuelto por la emt.
	 * @param json Json devuelto por la emt
	 * @return Token de acceso.
	 */
    private static String sacarToken(String json) {

        String[] array = json.split("\"accessToken\": \"");
        return array[1].substring(0, array[1].indexOf("\"")).trim();

    }

    /**
     * Saca el codigo que devuelve la peticion a los servicios de la emt
     * @param json Cuerpo de la peticion
     * @return Codigo de la peticion
     */
    public static String sacarCodigo (String json) {
		String[] array = json.split("\"code\": \"");
        return array[1].substring(0, array[1].indexOf("\"")).trim();
	}

	public static boolean recogerParadas() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
		String paradas;

		File file = new File (Constantes.URL_ARCHIVO_LISTA_PARADAS);

        boolean exito;

		try (BufferedWriter in= new BufferedWriter(new FileWriter(file))){
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.emtKey);
			
			HttpEntity<String> request = new HttpEntity<String>(headers);

			response = restTemplate.postForEntity(Constantes.URL_LISTA_PARADAS, request, String.class);
			
			paradas = response.getBody();

			if (!sacarCodigo(paradas).equals("00")){
				throw new RuntimeException("Error al hacer la peticion");
			}

			in.write(paradas);		
            
            exito = true;

		} catch (IOException e) {
			log.error("Error fatal: No se ha podido insertar los datos en el fichero. \nMessage: {}", e.getMessage());
            exito = false;
		}
		catch (RuntimeException e) {
			log.error("Error fatal: {}", e.getMessage());
            exito = false;
		}

        return exito;
    }
}
