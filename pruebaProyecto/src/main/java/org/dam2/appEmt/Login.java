package org.dam2.appEmt;

import org.dam2.appEmt.utilidades.Constantes;
import org.dam2.appEmt.utilidades.Passwords;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Login {
	
	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-ClientId", Passwords.CLIENT_ID);
			headers.set("passKey", Passwords.PASS_KEY);
			
			headers.forEach((k,v) ->  System.out.println(k + " " + v));
			
			HttpEntity<String> request = new HttpEntity<String>("headrers", headers);
			//HttpHeaders h = request.getHeaders();
			ResponseEntity<String> response = restTemplate.exchange(Constantes.URL_LOGIN, HttpMethod.GET, request, String.class);
		
			
			//h.forEach((k,v) ->  System.out.println("h:" + k + " " + v));
			
			String s = response.getBody();
			
			System.out.println(sacarToken(s));
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

    public static String sacarToken(String json) {

        String[] array = json.split("\"accessToken\": \"");
        return array[1].substring(0, array[1].indexOf("\"")).trim();

    }

}
