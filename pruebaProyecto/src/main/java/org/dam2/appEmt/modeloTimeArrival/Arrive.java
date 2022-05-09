package org.dam2.appEmt.modeloTimeArrival;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * {@link TimeArrivalBus}
 */
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

	@JsonProperty(value = "DistanceBus")
	public Integer getDistanceBus(){
		return distanceBus;
	}

	@JsonProperty(value = "positionTypeBus")
	public String getPositionTypeBus(){
		return positionTypeBus;
	}
	
	
	
	public GeoJsonPoint sacarPunto() {
		
		return new GeoJsonPoint (geometry.getCoordinates().get(0), geometry.getCoordinates().get(1));
		
	}
}
