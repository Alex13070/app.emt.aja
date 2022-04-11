package org.dam2.appEmt;

import java.lang.System.Logger;

import com.google.gson.Gson;

import org.dam2.appEmt.modeloTimeArrival.TimeArrivalBus;
import org.dam2.appEmt.utilidades.Variables;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/prueba")
public class PruebaController {

	//Logger logger = Logger.getLogger("MyLog");


	@SuppressWarnings("unused")
	@GetMapping ("/consultar/{parada}")
	public ResponseEntity<String> consultarParada (@PathVariable String parada)
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<String> responseAMandar;
		String s;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.key);
			
			HttpEntity<String> request = new HttpEntity<String>(Variables.JSON, headers);

			response = restTemplate.postForEntity(Variables.URL.replace("{}", parada), request, String.class);
			
			//GsonBuilder builder = new GsonBuilder(); 
			//builder.registerTypeAdapter(PointAdapter.class, new PointAdapter ()); 
			Gson gson = new Gson();
			
			s = response.getBody();
			
			//System.out.println(s);
			TimeArrivalBus t = gson.fromJson(s, TimeArrivalBus.class);
			//System.out.println(t.toString());
			//System.out.println(t.toString().replaceAll(",", ",\n"));
            
			responseAMandar = new ResponseEntity<>(s, HttpStatus.OK);

		} catch (Exception e) {
			//System.err.println("\n\n\n\nError" +  e.getMessage());
			//e.printStackTrace();
			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

		return responseAMandar;
	}

	@SuppressWarnings("unused")
	@GetMapping ("/listar-paradas")
	public ResponseEntity<String> listaParadas ()
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<String> responseAMandar;
		String s;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.key);
			
			HttpEntity<String> request = new HttpEntity<String>(headers);

			response = restTemplate.postForEntity(Variables.URL_LISTA_PARADAS, request, String.class);

			Gson gson = new Gson();
			
			s = response.getBody();
            
			responseAMandar = new ResponseEntity<>(s, HttpStatus.OK);

		} catch (Exception e) {
			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return responseAMandar;
	}

}
