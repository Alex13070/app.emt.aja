package org.dam2.appEmt.controladoresEmt;

public class PruebaQuery {

	static String parada = "5111";
	
	
	public static void main(String[] args) {

		//prueba para ver que saca el json bien
		PruebaController p =  new PruebaController();

		String lista = p.listaParadas().getBody();

		System.out.println(lista);

		/*
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.emtKey);
			
			HttpEntity<String> request = new HttpEntity<String>(Constantes.JSON, headers);
			
			ResponseEntity<String> response = restTemplate.postForEntity(Constantes.URL_TIME_ARRIVAL.replace("{}", parada), request, String.class);
			
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
