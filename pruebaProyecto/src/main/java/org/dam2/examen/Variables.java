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
	
	public static String key = "b7a159e9-33f8-41bd-b61b-0529ba6643cd";


}
