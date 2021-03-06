package org.dam2.appEmt.utilidades;

public class Constantes {
    
	/**
	 *	Json que hay que introducir para hacer la peticion de los tiempos de parada de la EMT
	 */
    public static final String JSON_TIME_ARRIVAL = "{\r\n"
			+ "		\"cultureInfo\":\"ES\",\r\n"
			+ "		\"Text_StopRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_EstimationsRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_IncidencesRequired_YN\":\"Y\", \r\n"
			+ "		\"DateTime_Referenced_Incidencies_YYYYMMDD\":\"????????\"\r\n"
			+ "	}";

	/**
	 *	URL para hacer login en los servidores de la EMT
	 */
	public static final String URL_LOGIN = "https://openapi.emtmadrid.es/v1/mobilitylabs/user/login/";
	
	/**
	 *	URL para pedir los tiempos de buses de una parada
	 */
	public static final String URL_TIME_ARRIVAL = "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/{}/arrives//";

	/*
	 *	URL para pedir un json con todas las paradas de la comunidad de madrid
	 */
	public static final String URL_LISTA_PARADAS = "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/list/";

	/*
	 *	URL para pedir un json con todas las paradas de una línea de bus concreta ordenadas de A a B
	 *  Dir 1 es de A a B y dir 2 es de B a A
	 */
	public static final String URL_LISTA_PARADAS_DE_UNA_LINEA = "https://openapi.emtmadrid.es/v1/transport/busemtmad/lines/{linea}/stops/{dir}/";

	/**
	 * Correo del administrador
	 */
	public final static String CORREO_ADMIN = "app.emt.aja@gmail.com";

	/**
	 * Password del administrador
	 */
	public final static String PASSWORD_ADMIN = "Autobus2022?";

	/**
	 * Secret key de cifrado JWT
	 */
	public final static String SECRET_KEY = "befjvncwo_nveutbn:oymth,potb,gvf";

	/**
	 * Tiempo de expiracion en milisegundos
	 */
	public final static Long TIEMPO_EXPIRACION = 
		2L  * /* Horas */
		60L * /* Minutos */
		60L * /* Segundos */
		1000L /* Milisegundos */;


	/**
	 * Direccion al archivo de lista de paradas
	 */
	public static final String URL_ARCHIVO_LISTA_PARADAS = "./Paradas.txt";

	/**
	 * Tiempo de expiracion del token de recuperacion
	 */
    public final static long TIEMPO_EXPIRACION_TOKEN_RECUPERACION = 180 * 1000;
	
}
