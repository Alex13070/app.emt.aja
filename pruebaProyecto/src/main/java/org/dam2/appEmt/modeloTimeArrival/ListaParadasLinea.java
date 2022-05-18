package org.dam2.appEmt.modeloTimeArrival;

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
public class ListaParadasLinea {
	
	@EqualsAndHashCode.Include
	private String code;

    //?
    //private ArrayList<Data> data;

    //private String line;
	
	//private String description;
	
	//private String datetime;
	
	

    /*
	public TimeArrivalBus() {
		super();
		this.code = null;
		this.description = null;
		this.datetime = null;
		this.data = new ArrayList<Data>();
	}
    */
}