package org.dam2.appEmt.modeloTimeArrival;


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
public class MoreInfo {
	
	@SerializedName(value = "@url")
	private String url;
	
	@SerializedName(value = "@type")
	private String mimeType;
	
	@SerializedName(value = "@length")
	private String length;

}
