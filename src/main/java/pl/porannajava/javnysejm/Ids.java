package pl.porannajava.javnysejm;

import pl.porannajava.javnysejm.http.UrlSupport;
import pl.porannajava.javnysejm.support.JsonSupport;

public enum Ids {
	/**
	 * 
	 */
	CLUB("kluby"),
	/**
	 * 
	 */
	DEPUTY("poslowie"),
	/**
	 * 
	 */
	SESSION("posiedzenia"),
	/**
	 * 
	 */
	SPEECH("wystapienia"),
	/**
	 * 
	 */
	DAY("dni"),
	/**
	 * 
	 */
	CONSIDERATION("rozpatrywania"),
	/**
	 * 
	 */
	VOTING("glosowania"),
	/**
	 * 
	 */
	ITEM("punkty"),
	/**
	 * 
	 */
	SPEAKER_POSITION("stanowiska"),
	/**
	 * 
	 */
	DOCUMENT("druki");

	String entityName;

	Ids(String entityName) {
		this.entityName = entityName;
	}

	public int[] getIds() {
		return JsonSupport.getIds(UrlSupport
				.getListUrlResponse(this.entityName).getBody());
	}

}
