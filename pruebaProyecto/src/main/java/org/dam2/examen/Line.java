package org.dam2.examen;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

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

	
	
}
