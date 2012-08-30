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


/**
 * "Punkty porządku dziennego" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Item extends IdEntity {

	/**
	 * Human readable item type representation
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public enum ItemType {

		/**
		 * Brak typu
		 */
		UNKNOWN(0),
		/**
		 * Pytania w sprawach bieżących
		 */
		QUESTION(1),
		/**
		 * Informacja bieżąca
		 */
		INFORMATION(2),
		/**
		 * Wszystko inne
		 */
		OTHER(3);
		int number;

		ItemType(int number) {
			this.number = number;
		}

		static ItemType fromNumber(int number) {
			switch (number) {
			case 0:
				return UNKNOWN;
			case 1:
				return QUESTION;
			case 2:
				return INFORMATION;
			case 3:
				return OTHER;
			default:
				throw new IllegalArgumentException("unknown value: " + number);
			}
		}
	}

	/**
	 * Human readable item document action representation
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public enum ItemDocumentAction {

		/**
		 * Brak typu
		 */
		UNKNOWN(0),
		/**
		 * Druk, będący przedmiotem punktu został przyjęty
		 */
		ACCEPTED(1),
		/**
		 * Druk, będący przedmiotem punktu został odrzucony
		 */
		REJECTED(2),
		/**
		 * Druk, będący przedmiotem punktu został skierowany do dalszych prac
		 */
		PROLONGED(3);
		int number;

		ItemDocumentAction(int number) {
			this.number = number;
		}

		static ItemDocumentAction fromNumber(int number) {
			switch (number) {
			case 0:
				return UNKNOWN;
			case 1:
				return ACCEPTED;
			case 2:
				return REJECTED;
			case 3:
				return PROLONGED;
			default:
				throw new IllegalArgumentException("unknown value: " + number);
			}
		}
	}

	private String number;
	private String title;
	private int sessionId;
	private ItemType type;
	private int documentId;
	private ItemDocumentAction itemDocumentAction;
	private int votingId;

	private int[] considerations;
	private int[] documents;

	protected Item(Builder builder) {
		super(builder);
		this.number = builder.getString("nr");
		this.title = builder.getString("tytul");
		this.sessionId = builder.getInt("posiedzenie_id");
		this.type = ItemType.fromNumber(builder.getInt("typ_id"));
		this.documentId = builder.getInt("druk_id");
		this.itemDocumentAction = ItemDocumentAction.fromNumber(builder
				.getInt("druk_akcja_id"));
		this.votingId = builder.getInt("glosowanie_id");

		this.considerations = builder.getIds("punkt", "rozpatrywania", this.id,
				builder.withConsiderations);
		this.documents = builder.getIds("punkt", "druki", this.id,
				builder.withDocuments);
	}

	public static class Builder extends IdEntityBuilder<Item> {

		protected boolean withConsiderations;
		protected boolean withDocuments;

		public Builder(int id) {
			super(id, "punkt");
		}

		public Builder withConsiderations() {
			this.withConsiderations = true;
			return this;
		}

		public Builder withDocuments() {
			this.withDocuments = true;
			return this;
		}

		@Override
		public Item build() {
			return new Item(this);
		}

	}

	/**
	 * Getter for "tytul" field
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Getter for "nr" field
	 */
	public String getNumber() {
		return this.number;
	}

	/**
	 * Getter for "posiedzenie_id" field
	 */
	public int getSessionId() {
		return this.sessionId;
	}

	/**
	 * Getter for "typ_id" field (converted to ItemType enum)
	 */
	public ItemType getType() {
		return this.type;
	}

	/**
	 * Getter for "druk_id" field
	 */
	public int getDocumentId() {
		return this.documentId;
	}

	/**
	 * Getter for "glosowanie_id" field
	 */
	public int getVotingId() {
		return this.votingId;
	}

	/**
	 * Getter for "druk_akcja_id" field (converted to ItemDocumentAction enum)
	 */
	public ItemDocumentAction getItemDocumentAction() {
		return this.itemDocumentAction;
	}

	/**
	 * Getter for consideration ids e.g.
	 * http://api.sejmometr.pl/punkt/406/rozpatrywania
	 */
	public int[] getConsiderations() {
		return this.considerations;
	}

	/**
	 * Getter for document ids e.g. http://api.sejmometr.pl/punkt/406/druki
	 */
	public int[] getDocuments() {
		return this.documents;
	}

}
