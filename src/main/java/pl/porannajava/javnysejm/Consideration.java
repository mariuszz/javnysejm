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

import org.joda.time.LocalTime;


/**
 * "Rozpatrywania" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Consideration extends IdEntity {

	private int sessionId;
	private int dayId;
	private int itemId;
	private String title;
	private int speechesCount;
	private int votingsCount;
	private LocalTime timeStart;
	private LocalTime timeStop;

	private final int[] speeches;
	private final int[] votings;

	protected Consideration(Builder builder) {
		super(builder);
		this.sessionId = builder.getInt("posiedzenie_id");
		this.dayId = builder.getInt("dzien_id");
		this.itemId = builder.getInt("punkt_id", false);
		this.title = builder.getString("tytul");
		this.speechesCount = builder.getInt("ilosc_wystapien");
		this.votingsCount = builder.getInt("ilosc_glosowan");
		this.timeStart = builder.getTime("time_start");
		this.timeStop = builder.getTime("time_stop");

		this.speeches = builder.getIds("rozpatrywanie", "wystapienia", this.id,
				builder.withSpeeches);
		this.votings = builder.getIds("rozpatrywanie", "glosowania", this.id,
				builder.withVotings);
	}

	public static class Builder extends IdEntityBuilder<Consideration> {

		protected boolean withSpeeches;
		protected boolean withVotings;

		public Builder(int id) {
			super(id, "rozpatrywanie");
		}

		public Builder withSpeeches() {
			this.withSpeeches = true;
			return this;
		}

		public Builder withVotings() {
			this.withVotings = true;
			return this;
		}

		@Override
		public Consideration build() {
			return new Consideration(this);
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
	 * Getter for "tytul" field
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Getter for "ilosc_wystapien" field
	 */
	public int getSpeechesCount() {
		return this.speechesCount;
	}

	/**
	 * Getter for "ilosc_glosowan" field
	 */
	public int getVotingsCount() {
		return this.votingsCount;
	}

	/**
	 * Getter for "time_start" field
	 */
	public LocalTime getTimeStart() {
		return this.timeStart;
	}

	/**
	 * Getter for "time_stop" field
	 */
	public LocalTime getTimeStop() {
		return this.timeStop;
	}

	/**
	 * Getter for speech ids e.g.
	 * http://api.sejmometr.pl/rozpatrywanie/857/wystapienia
	 */
	public int[] getSpeeches() {
		return this.speeches;
	}

	/**
	 * Getter for speech ids e.g.
	 * http://api.sejmometr.pl/rozpatrywanie/857/glosowania
	 */
	public int[] getVotings() {
		return this.votings;
	}

}
