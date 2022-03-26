package org.dam2.examen;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RestController
@RequestMapping("/prueba")
public class PruebaController {
	
	
	@GetMapping ("/consultar/{parada}")
	public ResponseEntity<TimeArrivalBus> consultarParada (@PathVariable String parada)
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<TimeArrivalBus> responseAMandar;
		String s;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.key);
			
			HttpEntity<String> request = new HttpEntity<String>(Variables.json, headers);

			response = restTemplate.postForEntity(Variables.url.replace("{}", parada), request, String.class);
			
			GsonBuilder builder = new GsonBuilder(); 
			//builder.registerTypeAdapter(PointAdapter.class, new PointAdapter ()); 
			Gson gson = builder.create();
			
			s = response.getBody();
			
			//System.out.println(s);
			
			TimeArrivalBus t = gson.fromJson(s, TimeArrivalBus.class);
			
            responseAMandar = new ResponseEntity<>(t, HttpStatus.OK);

			//System.out.println(t.toString().replaceAll(",", ",\n"));

			
		} catch (Exception e) {
			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		return responseAMandar;
	}

}
