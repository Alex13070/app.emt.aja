package org.dam2.appEmt.modeloTimeArrival;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;


@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Point {
	private String type;
	private ArrayList<Double> coordinates;
	
	
	public Point() {
		super();
		this.type = "Point";
		this.coordinates = new ArrayList<Double>();
	}
	
	

}
