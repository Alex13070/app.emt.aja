package org.dam2.appEmt;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PruebaQuery {
	
	static String json = "{\r\n"
			+ "		\"cultureInfo\":\"ES\",\r\n"
			+ "		\"Text_StopRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_EstimationsRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_IncidencesRequired_YN\":\"Y\", \r\n"
			+ "		\"DateTime_Referenced_Incidencies_YYYYMMDD\":\"????????\"\r\n"
			+ "	}";

	
	/*
	static String json = """
	{
		"cultureInfo":"ES",
		"Text_StopRequired_YN":"Y", 
		"Text_EstimationsRequired_YN":"Y", 
		"Text_IncidencesRequired_YN":"Y", 
		"DateTime_Referenced_Incidencies_YYYYMMDD":"????????"
	}""";
	*/
	static String url= "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/5112/arrives//";
	
	static String key = "9a7e29b5-6dc8-4c89-b391-b50931e4dcda";
	
	
	public static void main(String[] args) {

		//prueba para ver que saca el json bien
		PruebaController p =  new PruebaController();

		String lista = p.listaParadas().getBody();

		System.out.println(lista);
		
		//

		/*
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", key);
			
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
			
			GsonBuilder builder = new GsonBuilder(); 
			//builder.registerTypeAdapter(PointAdapter.class, new PointAdapter ()); 
			Gson gson = builder.create();
			
			String s = response.getBody();
			
			System.out.println(s);
			
			TimeArrivalBus t = gson.fromJson(s, TimeArrivalBus.class);
			
			System.out.println(t.toString().replaceAll(",", ",\n"));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		*/
	}

}
