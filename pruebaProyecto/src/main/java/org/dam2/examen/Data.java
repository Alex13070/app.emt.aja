package org.dam2.examen;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
//@NoArgsConstructor
public class Data {
	
	@SerializedName(value = "Arrive")
	private ArrayList<Arrive> arrive;
	
	@SerializedName(value = "StopInfo")
	private ArrayList<StopInfo> stopInfo;
	
	@SerializedName(value = "ExtraInfo")
	private ArrayList<Object> extraInfo;
	
	@SerializedName("Incident")
	private Incident incident;

	public Data() {
		super();
		this.arrive = new ArrayList<Arrive>();
		this.stopInfo = new ArrayList<StopInfo>();
		this.extraInfo = new ArrayList<Object>();
		this.incident = null;
	}
	
	
	
	
	

}
