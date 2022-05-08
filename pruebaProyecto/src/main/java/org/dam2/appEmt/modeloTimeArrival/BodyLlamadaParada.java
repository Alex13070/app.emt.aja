package org.dam2.appEmt.modeloTimeArrival;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * {@link TimeArrivalBus}
 */
@AllArgsConstructor
@Builder
@Data
public class BodyLlamadaParada {

	private String cultureInfo;
	private String Text_StopRequired_YN;
	private String Text_EstimationsRequired_YN;
	private String Text_IncidencesRequired_YN;
	private String DateTime_Referenced_Incidencies_YYYYMMDD;
}
