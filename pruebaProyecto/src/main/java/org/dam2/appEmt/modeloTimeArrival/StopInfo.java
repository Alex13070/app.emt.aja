package org.dam2.appEmt.modeloTimeArrival;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * {@link TimeArrivalBus}
 */
@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class StopInfo {
	
	private String label;
	
	@SerializedName(value = "StopLines")
	private StopLines stopLines;
	
	@SerializedName(value = "Description")
	private String description;
	
	private Point geometry;
	
	@SerializedName(value = "Direction")
	private String direction;

	@JsonProperty(value = "StopLines")
	public StopLines getStopLines(){
		return stopLines;
	}

	@JsonProperty(value = "Description")
	public String getDescription(){
		return description;
	}

	@JsonProperty(value = "Direction")
	public String getDirection(){
		return direction;
	}
	
	public GeoJsonPoint sacarPunto() {
		
		return new GeoJsonPoint (geometry.getCoordinates().get(0), geometry.getCoordinates().get(1));
		
	}



	public StopInfo() {
		super();
		this.label = null;
		this.stopLines = null;
		this.description = null;
		this.geometry = null;
		this.direction = null;
	}
	
	
	

}
