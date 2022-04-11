package org.dam2.appEmt.modeloTimeArrival;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@NoArgsConstructor
public class Arrive {

	
	private String line;
	private String stop;
	private String isHead;
	private String destination;
	private Integer deviation;
	private Integer bus;
	private Point geometry;
	private Integer estimateArrive;
	
	@SerializedName(value = "DistanceBus")	
	private Integer distanceBus;
	private String positionTypeBus;
	
	
	public org.springframework.data.geo.Point getGeometry() {
		
		//Pasar de un tipoo de punto a otro
		return new org.springframework.data.geo.Point (geometry.getCoordinates().get(0), geometry.getCoordinates().get(1));
		
	}
}
