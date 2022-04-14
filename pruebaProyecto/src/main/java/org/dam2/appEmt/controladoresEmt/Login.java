package org.dam2.appEmt.controladoresEmt;

import org.dam2.appEmt.utilidades.Constantes;
import org.dam2.appEmt.utilidades.Passwords;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sesion-emt")
public class Login {
	
	@PostMapping("/login")
	public ResponseEntity<String> login() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> respuesta;
		
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
			
			String token = sacarToken(s);

			respuesta = new ResponseEntity<>(token, HttpStatus.OK);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return respuesta;
	}

    private static String sacarToken(String json) {

        String[] array = json.split("\"accessToken\": \"");
        return array[1].substring(0, array[1].indexOf("\"")).trim();

    }

}
