/*
 * Copyright (C) 2012 Mariusz Zamolski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.porannajava.javnysejm;

import org.joda.time.LocalDateTime;

import pl.porannajava.javnysejm.http.UrlSupport;
import pl.porannajava.javnysejm.support.StringConverter;

/**
 * "Wystąpienie" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Speech extends IdEntity {

	private int sessionId;
	private int dayId;
	private int itemId;
	private int considerationId;
	private int speakerId;
	private int speakerFunctionId;
	private int deputyId;
	private int positionId;

	private LocalDateTime timeStart;
	private LocalDateTime timeStop;

	private String text = "";

	protected Speech(Builder builder) {
		super(builder);
		this.sessionId = builder.getInt("posiedzenie_id");
		this.dayId = builder.getInt("dzien_id");
		this.itemId = builder.getInt("punkt_id", false);
		this.considerationId = builder.getInt("rozpatrywanie_id");
		this.speakerFunctionId = builder.getInt("mowca_funkcja_id");
		this.speakerId = builder.getInt("mowca_id");
		this.deputyId = builder.getInt("posel_id", false);

		this.timeStart = builder.getDateTime("time_start", false);
		this.timeStop = builder.getDateTime("time_stop", false);

		this.positionId = builder.getInt("stanowisko_id");

		if (builder.withText) {
			this.text = StringConverter.getUnescapedString(UrlSupport
					.getUrlResponse("wystapienie", "tekst", this.id).getBody());
		}
	}

	public static class Builder extends IdEntityBuilder<Speech> {
		protected boolean withText;

		public Builder(int id) {
			super(id, "wystapienie");
		}

		public Builder withText() {
			this.withText = true;
			return this;
		}

		@Override
		public Speech build() {
			return new Speech(this);
		}

	}

	/**
	 * Getter for "posiedzenie_id" field
	 */
	public int getSessionId() {
		return this.sessionId;
	}

	/**
	 * Getter for "dzien_id" field
	 */
	public int getDayId() {
		return this.dayId;
	}

	/**
	 * Getter for "punkt_id" field
	 */
	public int getItemId() {
		return this.itemId;
	}

	/**
	 * Getter for "rozpatrywanie_id" field
	 */
	public int getConsiderationId() {
		return this.considerationId;
	}

	/**
	 * Getter for "mowca_id" field
	 */
	public int getSpeakerId() {
		return this.speakerId;
	}

	/**
	 * Getter for "mowca_funkcja_id" field
	 */
	public int getSpeakerFunctionId() {
		return this.speakerFunctionId;
	}

	/**
	 * Getter for "posel_id" field
	 */
	public int getDeputyId() {
		return this.deputyId;
	}

	/**
	 * Getter for "stanowisko_id" field
	 */
	public int getPositionId() {
		return this.positionId;
	}

	/**
	 * Getter for "time_start" field
	 */
	public LocalDateTime getTimeStart() {
		return this.timeStart;
	}

	/**
	 * Getter for "time_stop" field
	 */
	public LocalDateTime getTimeStop() {
		return this.timeStop;
	}

	/**
	 * Getter for text e.g. http://api.sejmometr.pl/wystapienie/7262/tekst
	 */
	public String getText() {
		return this.text;
	}

}
