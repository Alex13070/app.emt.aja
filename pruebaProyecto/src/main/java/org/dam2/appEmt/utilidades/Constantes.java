package org.dam2.appEmt.utilidades;

public class Constantes {
    
    public static final String JSON = "{\r\n"
			+ "		\"cultureInfo\":\"ES\",\r\n"
			+ "		\"Text_StopRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_EstimationsRequired_YN\":\"Y\", \r\n"
			+ "		\"Text_IncidencesRequired_YN\":\"Y\", \r\n"
			+ "		\"DateTime_Referenced_Incidencies_YYYYMMDD\":\"????????\"\r\n"
			+ "	}";

	public static final String URL_LOGIN = "https://openapi.emtmadrid.es/v1/mobilitylabs/user/login/";
	
	public static final String URL_TIME_ARRIVAL = "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/{}/arrives//";

	public static final String URL_LISTA_PARADAS = "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/list/";


}
