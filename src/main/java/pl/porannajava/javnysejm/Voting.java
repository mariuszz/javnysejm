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

import java.util.Map;

import org.joda.time.LocalDateTime;

import pl.porannajava.javnysejm.http.Response;
import pl.porannajava.javnysejm.http.UrlSupport;
import pl.porannajava.javnysejm.support.JsonSupport;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

/**
 * "Głosowanie" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Voting extends IdEntity {

	/**
	 * Human readable voting result representation
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public enum VotingResult {
		/**
		 * Za
		 */
		YES,
		/**
		 * Przeciw
		 */
		NO,
		/**
		 * Brak kworum
		 */
		INQUORATE;

		public static VotingResult of(int number) {
			switch (number) {
			case 1:
				return YES;
			case 2:
				return NO;
			default:
				return INQUORATE;
			}
		}
	}

	/**
	 * Human readable deputy voting result representation
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public enum DeputyVoting {
		/**
		 * Za
		 */
		YES,
		/**
		 * Przeciw
		 */
		NO,
		/**
		 * Wstrzymanie od głosu
		 */
		ABSTENTION,
		/**
		 * Nieobecność
		 */
		NOT_PARTICIPANT;

		public static DeputyVoting of(int number) {
			switch (number) {
			case 1:
				return YES;
			case 2:
				return NO;
			case 3:
				return ABSTENTION;
			default:
				return NOT_PARTICIPANT;
			}
		}
	}

	private String title;
	private int sessionId;
	private int dayId;
	private int itemId;
	private int considerationId;
	private int speechId;
	private int number;

	private LocalDateTime time;

	private VotingResult votingResult;

	private int totalCount;
	private int votersCount;
	private int supermajority;
	private int yesCount;
	private int noCount;
	private int abstentionCount;
	private int notParticipantCount;

	// deputyId, clubId, voting
	private Table<Integer, Integer, DeputyVoting> detailedResults;

	Voting(Builder builder) {
		super(builder);
		this.title = builder.getString("tytul");
		this.sessionId = builder.getInt("posiedzenie_id");
		this.dayId = builder.getInt("dzien_id");
		this.itemId = builder.getInt("punkt_id");
		this.considerationId = builder.getInt("rozpatrywanie_id");
		this.speechId = builder.getInt("wystapienie_id");
		this.number = builder.getInt("nr");
		this.time = builder.getDateTime("time");
		this.votingResult = VotingResult.of(builder.getInt("wynik"));
		this.totalCount = builder.getInt("l");
		this.votersCount = builder.getInt("g");
		this.supermajority = builder.getInt("wb");
		this.yesCount = builder.getInt("z");
		this.noCount = builder.getInt("p");
		this.abstentionCount = builder.getInt("w");
		this.notParticipantCount = builder.getInt("n");

		ImmutableTable.Builder<Integer, Integer, DeputyVoting> tableBuilder = new ImmutableTable.Builder<>();
		if (builder.withDetailedResults) {
			Response response = UrlSupport.getUrlResponse("glosowanie",
					"wyniki", this.id);
			Map<String, String>[] propertiesList = JsonSupport
					.getPropertiesArray(response.getBody());

			for (Map<String, String> properties : propertiesList) {
				Integer deputyId = Integer.valueOf(properties.get("posel_id"));
				Integer clubId = Integer.valueOf(properties.get("klub_id"));
				DeputyVoting deputyVoting = DeputyVoting.of(Integer.valueOf(
						properties.get("glos")).intValue());
				tableBuilder.put(deputyId, clubId, deputyVoting);
			}
		}
		this.detailedResults = tableBuilder.build();
	}

	public static class Builder extends IdEntityBuilder<Voting> {

		protected boolean withDetailedResults;

		public Builder(int id) {
			super(id, "glosowanie");
		}

		public Builder withDetailedResults() {
			this.withDetailedResults = true;
			return this;
		}

		@Override
		public Voting build() {
			return new Voting(this);
		}

	}

	/**
	 * Getter for "tytul" field
	 */
	public String getTitle() {
		return this.title;
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
	 * Getter for "wystapienie_id" field
	 */
	public int getSpeechId() {
		return this.speechId;
	}

	/**
	 * Getter for "nr" field
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Getter for "time" field
	 */
	public LocalDateTime getTime() {
		return this.time;
	}

	/**
	 * Getter for "wynik" field (converted to VotingResult enum)
	 */
	public VotingResult getVotingResult() {
		return this.votingResult;
	}

	/**
	 * Getter for "l" field
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * Getter for "g" field
	 */
	public int getVotersCount() {
		return this.votersCount;
	}

	/**
	 * Getter for "wb" field
	 */
	public int getSupermajority() {
		return this.supermajority;
	}

	/**
	 * Getter for "z" field
	 */
	public int getYesCount() {
		return this.yesCount;
	}

	/**
	 * Getter for "p" field
	 */
	public int getNoCount() {
		return this.noCount;
	}

	/**
	 * Getter for "w" field
	 */
	public int getAbstentionCount() {
		return this.abstentionCount;
	}

	/**
	 * Getter for "n" field
	 */
	public int getNotParticipantCount() {
		return this.notParticipantCount;
	}

	/**
	 * Getter for detailed voting results in human readable Table form: row:
	 * deputyId column: clubId value: DeputyVoting enum
	 */
	public Table<Integer, Integer, DeputyVoting> getDetailedResults() {
		return this.detailedResults;
	}
}
