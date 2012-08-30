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


/**
 * "Posiedzenie" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Session extends IdEntity {

	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private int pointsCount;
	private int votingsCount;

	private int[] items;
	private int[] days;
	private int[] considerations;
	private int[] speeches;
	private int[] votings;

	protected Session(Builder builder) {
		super(builder);
		this.title = builder.getString("tytul");
		this.startDate = builder.getDate("data_start");
		this.endDate = builder.getDate("data_stop");
		this.pointsCount = builder.getInt("ilosc_punktow");
		this.votingsCount = builder.getInt("ilosc_glosowan");

		this.items = builder.getIds("posiedzenie", "punkty", this.id,
				builder.withItems);
		this.days = builder.getIds("posiedzenie", "dni", this.id,
				builder.withDays);
		this.considerations = builder.getIds("posiedzenie", "rozpatrywania",
				this.id, builder.withConsiderations);
		this.speeches = builder.getIds("posiedzenie", "wystapienia", this.id,
				builder.withSpeeches);
		this.votings = builder.getIds("posiedzenie", "glosowania", this.id,
				builder.withVotings);

	}

	/**
	 * Builder for Session entity
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public static class Builder extends IdEntityBuilder<Session> {

		protected boolean withItems;
		protected boolean withSpeeches;
		protected boolean withVotings;
		protected boolean withDays;
		protected boolean withConsiderations;

		public Builder(int id) {
			super(id, "posiedzenie");
		}

		public Builder withItems() {
			this.withItems = true;
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

		public Builder withDays() {
			this.withDays = true;
			return this;
		}

		public Builder withConsiderations() {
			this.withConsiderations = true;
			return this;
		}

		@Override
		public Session build() {
			return new Session(this);
		}

	}

	/**
	 * Getter for "tytul" field
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Getter for "data_start" field
	 */
	public LocalDate getStartDate() {
		return this.startDate;
	}

	/**
	 * Getter for "data_stop" field
	 */
	public LocalDate getEndDate() {
		return this.endDate;
	}

	/**
	 * Getter for "ilosc_punktow" field
	 */
	public int getPointsCount() {
		return this.pointsCount;
	}

	/**
	 * Getter for "ilosc_glosowan" field
	 */
	public int getVotingsCount() {
		return this.votingsCount;
	}

	/**
	 * Getter for item ids e.g. http://api.sejmometr.pl/posiedzenie/7/punkty
	 */
	public int[] getItems() {
		return this.items;
	}

	/**
	 * Getter for day ids e.g. http://api.sejmometr.pl/posiedzenie/7/dni
	 */
	public int[] getDays() {
		return this.days;
	}

	/**
	 * Getter for consideration ids e.g.
	 * http://api.sejmometr.pl/posiedzenie/7/rozpatrywania
	 */
	public int[] getConsiderations() {
		return this.considerations;
	}

	/**
	 * Getter for speech ids e.g.
	 * http://api.sejmometr.pl/posiedzenie/7/wystapienia
	 */
	public int[] getSpeeches() {
		return this.speeches;
	}

	/**
	 * Getter for voting ids e.g.
	 * http://api.sejmometr.pl/posiedzenie/7/glosowania
	 */
	public int[] getVotings() {
		return this.votings;
	}

}
