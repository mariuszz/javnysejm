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

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;


/**
 * "Dzień" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Day extends IdEntity {

	private LocalDate date;
	private LocalDateTime timeStart;
	private LocalDateTime timeStop;
	private int documentItemId;
	private int sessionId;

	private int[] considerations;
	private int[] speeches;
	private int[] votings;

	protected Day(Builder builder) {
		super(builder);
		this.date = builder.getDate("data");
		this.timeStart = builder.getDateTime("time_start");
		this.timeStop = builder.getDateTime("time_stop");
		this.documentItemId = builder.getInt("dokument_id");
		this.sessionId = builder.getInt("posiedzenie_id");

		this.considerations = builder.getIds("dzien", "rozpatrywania", this.id,
				builder.withConsiderations);

		this.speeches = builder.getIds("dzien", "wystapienia", this.id,
				builder.withSpeeches);

		this.votings = builder.getIds("dzien", "glosowania", this.id,
				builder.withVotings);

	}

	public static class Builder extends IdEntityBuilder<Day> {

		protected boolean withConsiderations;
		protected boolean withSpeeches;
		protected boolean withVotings;

		public Builder(int id) {
			super(id, "dzien");
		}

		public Builder withConsiderations() {
			this.withConsiderations = true;
			return this;
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
		public Day build() {
			return new Day(this);
		}

	}

	/**
	 * Getter for "data" field
	 */
	public LocalDate getDate() {
		return this.date;
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
	 * Getter for "dokument_id" field
	 */
	public int getDocumentItemId() {
		return this.documentItemId;
	}

	/**
	 * Getter for "posiedzenie_id" field
	 */
	public int getSessionId() {
		return this.sessionId;
	}

	/**
	 * Getter for consideration ids e.g.
	 * http://api.sejmometr.pl/dzien/10272/rozpatrywania
	 */
	public int[] getConsiderations() {
		return this.considerations;
	}

	/**
	 * Getter for speech ids e.g.
	 * http://api.sejmometr.pl/dzien/10272/wystapienia
	 */
	public int[] getSpeeches() {
		return this.speeches;
	}

	/**
	 * Getter for voting ids e.g. http://api.sejmometr.pl/dzien/10272/glosowania
	 */
	public int[] getVotings() {
		return this.votings;
	}
}
