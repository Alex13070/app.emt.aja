package org.dam2.appEmt.controladoresEmt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import org.dam2.appEmt.modeloTimeArrival.TimeArrivalBus;
import org.dam2.appEmt.utilidades.Constantes;
import org.dam2.appEmt.utilidades.Variables;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.asm.Advice.Unused;

@RestController
@RequestMapping("/prueba")
public class PruebaController {

	//Logger logger = Logger.getLogger("MyLog");
	private final String secret = "mySecretKey";
	
	private final long tiempo = 600000;

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
	//@GetMapping ("/listar-paradas")
	@GetMapping ("/login/listar-paradas")

	public ResponseEntity<String> listaParadas ()
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<String> responseAMandar;
		String s;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.emtKey);
			
			HttpEntity<String> request = new HttpEntity<String>(headers);

			response = restTemplate.postForEntity(Constantes.URL_LISTA_PARADAS, request, String.class);

			Gson gson = new Gson();
			
			s = response.getBody();
            
			responseAMandar = new ResponseEntity<>(s, HttpStatus.OK);

		} catch (Exception e) {
			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return responseAMandar;
	}

	@SuppressWarnings("unused")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		
    	
    	String token = ""; 
    	//comprobariamos en la base de datos
    	if (username.equals("client") && pwd.equals("client"))
    		token = getJWTToken(username);
			
		return new ResponseEntity<String>(token, HttpStatus.OK);
		
	}
	private String getJWTToken(String username) {


		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("CLIENT");
		
		String token = Jwts
				.builder()
				.setId("EmpAJA")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tiempo))
				.signWith(SignatureAlgorithm.HS512,
						secret.getBytes()).compact();

		return "Bearer " + token;
	}
}
