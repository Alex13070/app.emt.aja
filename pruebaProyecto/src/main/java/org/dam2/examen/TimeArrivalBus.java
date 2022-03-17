package org.dam2.examen;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class TimeArrivalBus {
	
	@EqualsAndHashCode.Include
	private String code;
	
	private String description;
	
	private String datetime;
	
	@SerializedName(value = "data")
	private ArrayList<Data> data;

	public TimeArrivalBus() {
		super();
		this.code = null;
		this.description = null;
		this.datetime = null;
		this.data = new ArrayList<Data>();
	}
	
	

}
