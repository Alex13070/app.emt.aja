package org.dam2.examen;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@lombok.Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
public class Incident {
	
	@SerializedName(value = "ListaIncident")
	private ListaIncident listaIncident;

}
