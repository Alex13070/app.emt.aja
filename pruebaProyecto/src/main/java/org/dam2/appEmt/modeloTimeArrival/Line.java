package org.dam2.appEmt.modeloTimeArrival;


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
public class Line {
	
	@SerializedName(value = "Label")
	private String label;
	private String line;
	@SerializedName(value = "Description")
	private String description;
	private Integer distance;
	private String to;

	@JsonProperty(value = "Label")
	public String getLabel() {
		return label;
	}

	@JsonProperty(value = "Description")
	public String getDescription() {
		return description;
	}

	
	
}
