package org.dam2.examen;

public class Variables {
    
    public static String json = "{\r\n"
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
	
	public static String url= "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/{}/arrives//";

	public static String urlListaParadas= "https://openapi.emtmadrid.es/v1/transport/busemtmad/stops/list/";
	
	public static String key = "aa27744a-b8af-11ec-a75c-02dc46838a9c";


}
