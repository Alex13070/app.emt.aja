package org.dam2.appEmt.modeloTimeArrival;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

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
public class StopLines {
	
	@SerializedName(value = "Data")
	private ArrayList<Line> data;

	@JsonProperty(value = "Data")
	public ArrayList<Line> getData() {
		return data;
	}

	public StopLines() {
		super();
		this.data = new ArrayList<Line>();
	}
	
	

}
