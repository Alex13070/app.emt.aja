package org.dam2.appEmt.modeloTimeArrival;

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
public class IncidentData {
	
	private String title;
	private String guid;
	private String description;
	private String pubDate;
	private String rssFrom;
	private String rssTo;
	private String cause;
	private String effect;
	private MoreInfo moreInfo;
	

}
