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

	@JsonProperty(value = "Arrive")
	public ArrayList<Arrive> getArrive() {
		return arrive;
	}

	@JsonProperty(value = "StopInfo")
	public ArrayList<StopInfo> getStopInfo() {
		return stopInfo;
	}

	@JsonProperty(value = "ExtraInfo")
	public ArrayList<Object> getExtraInfo() {
		return extraInfo;
	}

	@JsonProperty(value = "Incident")
	public Incident getIncident() {
		return incident;
	}

	public Data() {
		super();
		this.arrive = new ArrayList<Arrive>();
		this.stopInfo = new ArrayList<StopInfo>();
		this.extraInfo = new ArrayList<Object>();
		this.incident = null;
	}
	
	
	
	
	

}
