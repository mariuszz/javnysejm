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
 * "Poseł" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Deputy extends IdEntity {

	private String code;
	private String fullName;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String birthPlace;
	private int clubId;
	private int electoralDistrict;
	private int votesNumber;
	private String profession;

	private int[] speeches;
	private int[] votings;
	private DeputyCommittee[] deputyCommittees;
	private DeputyFinancialStatement[] deputyFinancialStatements;
	private DeputyFinancialBenefitsReport[] deputyFinancialBenefitsReports;

	protected Deputy(Builder builder) {
		super(builder);
		this.code = builder.getString("slug");
		this.fullName = builder.getString("nazwa");
		this.firstName = builder.getString("imie");
		this.lastName = builder.getString("nazwisko");
		this.birthDate = builder.getDate("data_urodzenia");
		this.birthPlace = builder.getString("miejsce_urodzenia");
		this.clubId = builder.getInt("klub_id");
		this.electoralDistrict = builder.getInt("pkw_nr_okregu");
		this.votesNumber = builder.getInt("pkw_liczba_glosow");
		this.profession = builder.getString("pkw_zawod");

		this.speeches = builder.getIds("posel", "wystapienia", this.id,
				builder.withSpeeches);

		this.votings = builder.getIds("posel", "glosowania", this.id,
				builder.withVotings);

		this.deputyCommittees = new DeputyCommittee.Builder(this.id,
				builder.withDeputyCommittees).build();
		this.deputyFinancialStatements = new DeputyFinancialStatement.Builder(
				this.id, builder.withDeputyFinancialStatements).build();
		this.deputyFinancialBenefitsReports = new DeputyFinancialBenefitsReport.Builder(
				this.id, builder.withDeputyFinancialBenefitsReports).build();

	}

	/**
	 * Getter for "slug" field
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Getter for "nazwa" field
	 */
	public String getFullName() {
		return this.fullName;
	}

	/**
	 * Getter for "imie" field
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Getter for "nazwisko" field
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Getter for "data_urodzenia" field
	 */
	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	/**
	 * Getter for "miejsce_urodzenia" field
	 */
	public String getBirthPlace() {
		return this.birthPlace;
	}

	/**
	 * Getter for "klub_id" field
	 */
	public int getClubId() {
		return this.clubId;
	}

	/**
	 * Getter for "pkw_nr_okregu" field
	 */
	public int getElectoralDistrict() {
		return this.electoralDistrict;
	}

	/**
	 * Getter for "pkw_liczba_glosow" field
	 */
	public int getVotesNumber() {
		return this.votesNumber;
	}

	/**
	 * Getter for "pkw_zawod" field
	 */
	public String getProfession() {
		return this.profession;
	}

	/**
	 * Getter for speaking ids e.g.
	 * http://api.sejmometr.pl/posel/326/wystapienia
	 */
	public int[] getSpeeches() {
		return this.speeches;
	}

	/**
	 * Getter for voting ids e.g. http://api.sejmometr.pl/posel/326/glosowania
	 */
	public int[] getVotings() {
		return this.votings;
	}

	/**
	 * Getter for deputy committees e.g.
	 * http://api.sejmometr.pl/posel/326/komisje
	 */
	public DeputyCommittee[] getDeputyCommittees() {
		return this.deputyCommittees;
	}

	/**
	 * Getter for deputy financial statements e.g.
	 * http://api.sejmometr.pl/posel/326/oswiadczenia_majatkowe
	 */
	public DeputyFinancialStatement[] getDeputyFinancialStatements() {
		return this.deputyFinancialStatements;
	}

	/**
	 * Getter for deputy financial statements e.g.
	 * http://api.sejmometr.pl/posel/326/rejestr_korzysci
	 */
	public DeputyFinancialBenefitsReport[] getDeputyFinancialBenefitsReports() {
		return this.deputyFinancialBenefitsReports;
	}

	/**
	 * Builder for Deputy entity
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public static class Builder extends IdEntityBuilder<Deputy> {

		protected boolean withSpeeches;
		protected boolean withVotings;
		protected boolean withDeputyCommittees;
		protected boolean withDeputyFinancialStatements;
		protected boolean withDeputyFinancialBenefitsReports;

		public Builder(int id) {
			super(id, "posel");
		}

		public Builder withSpeeches() {
			this.withSpeeches = true;
			return this;
		}

		public Builder withVotings() {
			this.withVotings = true;
			return this;
		}

		public Builder withDeputyCommittees() {
			this.withDeputyCommittees = true;
			return this;
		}

		public Builder withDeputyFinancialStatements() {
			this.withDeputyFinancialStatements = true;
			return this;
		}

		public Builder withDeputyFinancialBenefitsReports() {
			this.withDeputyFinancialBenefitsReports = true;
			return this;
		}

		@Override
		public Deputy build() {
			return new Deputy(this);
		}
	}

}
