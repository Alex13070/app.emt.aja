package org.dam2.examen;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class ListaIncident {

	private ArrayList<IncidentData> data;

	public ListaIncident() {
		super();
		this.data = new ArrayList<IncidentData>();
	}
	
	
}
