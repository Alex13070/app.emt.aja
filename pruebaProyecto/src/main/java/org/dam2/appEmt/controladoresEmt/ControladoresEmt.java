package org.dam2.appEmt.controladoresEmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.dam2.appEmt.configuration.filter.CustomAuthorizationFilter;
import org.dam2.appEmt.configuration.logs.Logs;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.servicios.IUsuarioService;
import org.dam2.appEmt.modeloTimeArrival.ListaParadasLinea;
import org.dam2.appEmt.modeloTimeArrival.TimeArrivalBus;
import org.dam2.appEmt.mongo.model.SaleData;
import org.dam2.appEmt.mongo.service.ISaleDataService;
import org.dam2.appEmt.utilidades.Constantes;
import org.dam2.appEmt.utilidades.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/controladores-emt")
public class ControladoresEmt {

	/**
	 * Microservicio Mongo para guardar datos vendibles
	 */
	@Autowired
	private ISaleDataService saleDataService;

	/**
	 * Microservicio de usuario
	 */
	@Autowired 
	private IUsuarioService usuarioService;

	/**
	 * Controlador de la emt que se encarfa de consultar la informacion de la parada pedida.
	 * @param parada Codigo de parada.
	 * @return ResponseEntity con un string en el que se se mandara la informacion de la parada en caso de que 
	 * 		   la peticion sea correcta, en caso contrario, se mandara un error.
	 */
	@GetMapping ("/consultar-parada/{parada}")
	public ResponseEntity<TimeArrivalBus> consultarParada (@PathVariable String parada, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<TimeArrivalBus> responseAMandar;
		String s;

		String msg;

		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.emtKey);
			
			HttpEntity<String> request = new HttpEntity<String>(Constantes.JSON_TIME_ARRIVAL, headers);

			response = restTemplate.postForEntity(Constantes.URL_TIME_ARRIVAL.replace("{}", parada), request, String.class);

			Gson gson = new Gson();
			
			s = response.getBody();
			
			TimeArrivalBus tab = gson.fromJson(s, TimeArrivalBus.class);
			
			String idUsuario = CustomAuthorizationFilter.getUserIdFromToken(token);

			Optional<Usuario> usuario = usuarioService.findById(idUsuario);
			
			if (usuario.isPresent()) {
				guardarDatos (tab, usuario.get());
			}
			else {
				throw new UsernameNotFoundException("El nombre del usuario no existe.");
			}
            
			msg = "Parada consultada";
			responseAMandar = new ResponseEntity<>(tab, HttpStatus.OK);

		} catch (JsonSyntaxException e) {
			msg = "Error al parsear JSON";
			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		catch (Exception rce) {
			msg = "Error al consultar parada" + ((rce.getMessage() != null)?rce.getMessage():"");
			responseAMandar = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		new Thread(new Logs(LocalDateTime.now(), "ControladoresEmt(consultarParada)", msg)).start();
		return responseAMandar;
	}

	/**
	 * Guarda datos en la base de datos mongo
	 * @param tab Datos de la emt
	 * @param usuario Datos de usuario
	 */
	private void guardarDatos(TimeArrivalBus tab, Usuario usuario) {

		SaleData saleData = SaleData.builder()
			.fecha(LocalDateTime.now())
			.edad(LocalDate.now().getYear() - usuario.getFechaNacimiento().getYear())
			.sexo(usuario.getSexo())
			.geometry(tab.getData().get(0).getStopInfo().get(0).sacarPunto())
			.build();

		saleDataService.insert(saleData);
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
		File archivo = new File (Constantes.URL_ARCHIVO_LISTA_PARADAS);
		
		String msg;

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
			msg = "Paradas listadas";

		} catch (Exception e) {
			responseAMandar = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			msg = "Error al listar paradas " + ((e.getMessage() != null)?e.getMessage():""); 
		}

		new Thread(new Logs(LocalDateTime.now(), "ControladoresEmt(listarParadas)", msg)).start();
		return responseAMandar;
	}

	/**
	 * Consulta las lineas de una parada
	 * @param linea Linea de la emt
	 * @param dir Direccion
	 * @return String con el json de paradas de una linea de la emt
	 */
	@GetMapping ("/consultar-linea/{linea}/{dir}")
	public ResponseEntity<String> listarParadasDeUnaLinea (@PathVariable String linea, @PathVariable String dir)
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response;
        ResponseEntity<String> responseAMandar;
		String s;
		String msg;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accessToken", Variables.emtKey);
			
			response = restTemplate.exchange(Constantes.URL_LISTA_PARADAS_DE_UNA_LINEA.replace("{linea}", linea).replace("{dir}", dir), HttpMethod.GET, new HttpEntity<Object>(headers), String.class);
			
			//response = restTemplate.getForEntity(Constantes.URL_LISTA_PARADAS_DE_UNA_LINEA.replace("{}", linea), String.class);

			Gson gson = new Gson();
			
			s = response.getBody();
			
			//lo pasa a esto solo para coger luego el mumero
			ListaParadasLinea tab = gson.fromJson(s, ListaParadasLinea.class);

			//TimeArrivalBus tab = response.getBody();

			if (!tab.getCode().equals("00")) {

				throw new RuntimeException("Error de peticion: " + s);
			}

			msg = "Parads de linea listada";
            
			responseAMandar = new ResponseEntity<>(s, HttpStatus.OK);

		} catch (JsonSyntaxException e) {

			log.error(e.getMessage());
			responseAMandar = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			msg = "Error al listar las paradas de la linea " + linea;
			
		}
		catch (Exception e) {
			log.error(e.getMessage());
			responseAMandar = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			msg = "Error al listar paradas de la linea " + linea + " " + ((e.getMessage() != null)?e.getMessage():""); 
		}

		new Thread(new Logs(LocalDateTime.now(), "ControladoresEmt(listarParadasDeUnaLinea)", msg)).start();
		return responseAMandar;
	}
	
}