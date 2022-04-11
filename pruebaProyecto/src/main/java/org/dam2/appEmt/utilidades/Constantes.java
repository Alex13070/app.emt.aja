package org.dam2.appEmt.utilidades;

public class Constantes {
    
	/*
	 *	Json que hay que introducir para hacer la peticion de los tiempos de parada de la EMT
	 */
    public static final String JSON_TIME_ARRIVAL = "{\r\n"
			+ "		\"cultureInfo\":\"ES\",\r\n"
			+ "		\"Text_StopRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_EstimationsRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_IncidencesRequired_YN\":\"Y\", \r\n"
			+ "		\"DateTime_Referenced_Incidencies_YYYYMMDD\":\"????????\"\r\n"
			+ "	}";

	/*
	 *	URL para hacer login en los servidores de la EMT
	 */
	public static final String URL_LOGIN = "https://openapi.emtmadrid.es/v1/mobilitylabs/user/login/";
	
	/*
	 *	URL para pedir los tiempos de buses de una parada
	 */
	public static final String URL_TIME_ARRIVAL = "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/{}/arrives//";

	/*
	 *	URL para pedir un json con todas las paradas de la comunidad de madrid
	 */
	public static final String URL_LISTA_PARADAS = "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/list/";


}
