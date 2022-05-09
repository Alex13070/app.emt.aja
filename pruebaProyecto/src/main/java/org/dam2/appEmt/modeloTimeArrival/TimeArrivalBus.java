package org.dam2.appEmt.modeloTimeArrival;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * Datos a recoger para ver los tiempos de los autobuses.
 */
@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class TimeArrivalBus {
	
	@EqualsAndHashCode.Include
	private String code;
	
	private String description;
	
	private String datetime;
	
	private ArrayList<Data> data;

	public TimeArrivalBus() {
		super();
		this.code = null;
		this.description = null;
		this.datetime = null;
		this.data = new ArrayList<Data>();
	}
}
