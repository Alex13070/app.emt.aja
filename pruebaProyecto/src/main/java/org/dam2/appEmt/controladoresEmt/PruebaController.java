package org.dam2.appEmt.controladoresEmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
// import java.io.IOException;
// import java.util.Date;
// import java.util.List;
// import java.util.logging.Logger;
// import java.util.stream.Collectors;

import com.google.gson.Gson;

import org.dam2.appEmt.modeloTimeArrival.TimeArrivalBus;
import org.dam2.appEmt.utilidades.Constantes;
import org.dam2.appEmt.utilidades.Variables;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import net.bytebuddy.asm.Advice.Unused;

@RestController
@RequestMapping("/prueba")
public class PruebaController {

	//Logger logger = Logger.getLogger("MyLog");


	@SuppressWarnings("unused")
	//@GetMapping ("/consultar/{parada}")
	@GetMapping ("/login/consultar/{parada}")

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
			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
