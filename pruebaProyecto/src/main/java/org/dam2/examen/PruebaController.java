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
	/*
	static String json = "{\r\n"
			+ "		\"cultureInfo\":\"ES\",\r\n"
			+ "		\"Text_StopRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_EstimationsRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_IncidencesRequired_YN\":\"Y\", \r\n"
			+ "		\"DateTime_Referenced_Incidencies_YYYYMMDD\":\"????????\"\r\n"
			+ "	}";
            */
	
	static String json = """
	{
		"cultureInfo":"ES",
		"Text_StopRequired_YN":"Y", 
		"Text_EstimationsRequired_YN":"Y", 
		"Text_IncidencesRequired_YN":"Y", 
		"DateTime_Referenced_Incidencies_YYYYMMDD":"????????"
	}""";
	
	
	static String url= "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/{}/arrives//";
	
	static String key = "b7a159e9-33f8-41bd-b61b-0529ba6643cd";
	
	@GetMapping ("/consultar/{parada}")
	public ResponseEntity<TimeArrivalBus> consultarParada (@PathVariable String parada)
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<TimeArrivalBus> responseAMandar;
		String s;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", key);
			
			HttpEntity<String> request = new HttpEntity<String>(json, headers);

			response = restTemplate.postForEntity(url.replace("{}", parada), request, String.class);
			
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
