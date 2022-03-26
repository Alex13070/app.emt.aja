package org.dam2.examen;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

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

	
	
	public org.springframework.data.geo.Point getGeometry() {
		
		return new org.springframework.data.geo.Point (geometry.getCoordinates().get(0), geometry.getCoordinates().get(1));
		
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
