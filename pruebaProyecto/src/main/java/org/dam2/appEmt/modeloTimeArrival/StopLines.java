package org.dam2.appEmt.modeloTimeArrival;

import java.util.ArrayList;

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

	public StopLines() {
		super();
		this.data = new ArrayList<Line>();
	}
	
	

}
