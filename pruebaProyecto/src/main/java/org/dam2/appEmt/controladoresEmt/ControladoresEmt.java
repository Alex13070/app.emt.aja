package org.dam2.appEmt.controladoresEmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.dam2.appEmt.modeloTimeArrival.TimeArrivalBus;
import org.dam2.appEmt.utilidades.Constantes;
import org.dam2.appEmt.utilidades.Variables;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/controladores-emt")
public class ControladoresEmt {

	/**
	 * Controlador de la emt que se encarfa de consultar la informacion de la parada pedida.
	 * @param parada Codigo de parada.
	 * @return ResponseEntity con un string en el que se se mandara la informacion de la parada en caso de que 
	 * 		   la peticion sea correcta, en caso contrario, se mandara un error.
	 */
	@GetMapping ("/consultar-parada/{parada}")
	public ResponseEntity<String> consultarParada (@PathVariable String parada)
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<String> responseAMandar;
		String s;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.emtKey);
			
			HttpEntity<String> request = new HttpEntity<String>(Constantes.JSON_TIME_ARRIVAL, headers);

			response = restTemplate.postForEntity(Constantes.URL_TIME_ARRIVAL.replace("{}", parada), request, String.class);

			Gson gson = new Gson();
			
			s = response.getBody();
			
			gson.fromJson(s, TimeArrivalBus.class);
			
            
			responseAMandar = new ResponseEntity<>(s, HttpStatus.OK);

		} catch (JsonSyntaxException e) {

			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		catch (RestClientException rce) {
			
			responseAMandar = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return responseAMandar;
	}

	/**
	 * Lectura de archivo en el que estan todas las paradas introducidas.
	 * @return Listado con todas las paradas. En caso contrario, mensaje de error.
	 */
	@SuppressWarnings("unused")
	@GetMapping ("/listar-paradas")
	public ResponseEntity<String> listaParadas ()
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<String> responseAMandar;
		String s;
		File archivo = new File (Variables.urlListaParadas);
		
		try(
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
		){
			
			String linea;
			StringBuffer textoParadas = new StringBuffer();
			while((linea=br.readLine())!=null){
				textoParadas.append(linea);
			}

			responseAMandar = new ResponseEntity<>(textoParadas.toString(), HttpStatus.OK);

		} catch (Exception e) {
			responseAMandar = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseAMandar;
	}

	/*
	//quitar, lo pongo para hacer pruebas
	public static void main(String[] args) {
		PruebaController p = new PruebaController();
		ResponseEntity<String> resp = p.listaParadas();
		System.out.println(resp.getBody().toString());
	}
	*/
}
