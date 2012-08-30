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
 * "Druk" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class Document extends IdEntity {

	/**
	 * Human readable document type representation
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public enum DocumentType {
		/**
		 * projekt ustawy
		 */
		BILL_PROJECT(1),
		/**
		 * projekt uchwały
		 */
		RESOLUTION_PROJECT(2),
		/**
		 * wniosek
		 */
		PROPOSAL(3),
		/**
		 * inny dokument
		 */
		OTHER_DOCUMENT(5),
		/**
		 * ratyfikacja w formie zawiadomienia
		 */
		RATIFICATION(6),
		/**
		 * sprawozdanie komisji
		 */
		COMMITTEE_REPORT(7),
		/**
		 * stanowisko Senatu
		 */
		SENATE_STANCE(8),
		/**
		 * dodatkowe sprawozdanie komisji
		 */
		ADDITIONAL_COMMITTEE_REPORT(9);
		int number;

		DocumentType(int number) {
			this.number = number;
		}

		static DocumentType fromNumber(int number) {
			switch (number) {
			case 1:
				return BILL_PROJECT;
			case 2:
				return RESOLUTION_PROJECT;
			case 3:
				return PROPOSAL;
			case 5:
				return OTHER_DOCUMENT;
			case 6:
				return RATIFICATION;
			case 7:
				return COMMITTEE_REPORT;
			case 8:
				return SENATE_STANCE;
			case 9:
				return ADDITIONAL_COMMITTEE_REPORT;
			default:
				throw new IllegalArgumentException("unknown value: " + number);
			}
		}
	}

	/**
	 * Human readable author type representation
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public enum DocumentAuthorType {
		/**
		 * Rada Ministrów
		 */
		MINISTRY(1),
		/**
		 * Obywatele (projekt obywatelski)
		 */
		CITIZENRY(2),
		/**
		 * Komisje sejmowe
		 */
		COMMITTEE(3),
		/**
		 * Senat
		 */
		SENATE(4),
		/**
		 * Posłowie (projekty poselskie)
		 */
		DEPUTY(5),
		/**
		 * Prezydent
		 */
		PRESIDENT(6),
		/**
		 * Prezydium Sejmu
		 */
		PRESIDIUM(7);

		int number;

		DocumentAuthorType(int number) {
			this.number = number;
		}

		static DocumentAuthorType fromNumber(int number) {
			switch (number) {
			case 1:
				return MINISTRY;
			case 2:
				return CITIZENRY;
			case 3:
				return COMMITTEE;
			case 4:
				return SENATE;
			case 5:
				return DEPUTY;
			case 6:
				return PRESIDENT;
			case 7:
				return PRESIDIUM;
			default:
				throw new IllegalArgumentException("unknown value: " + number);
			}
		}
	}

	private String number;
	private String title;
	private String description;
	private int documentItemId;
	private DocumentType documentType;
	private DocumentAuthorType documentAuthorType;

	protected Document(Builder builder) {
		super(builder);
		this.number = builder.getString("nr");
		this.title = builder.getString("tytul");
		this.description = builder.getString("opis");
		this.documentItemId = builder.getInt("dokument_id");
		this.documentType = DocumentType.fromNumber(builder.getInt("typ_id"));
		this.documentAuthorType = DocumentAuthorType.fromNumber(builder
				.getInt("autor_typ_id"));
	}

	public static class Builder extends IdEntityBuilder<Document> {
		public Builder(int id) {
			super(id, "druk");
		}

		@Override
		public Document build() {
			return new Document(this);
		}

	}

	/**
	 * Getter for "nr" field
	 */
	public String getNumber() {
		return this.number;
	}

	/**
	 * Getter for "tytul" field
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Getter for "opis" field
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Getter for "dokument_id" field
	 */
	public int getDocumentItemId() {
		return this.documentItemId;
	}

	/**
	 * Getter for "typ_id" field (converted to DocumentType enum)
	 */
	public DocumentType getDocumentType() {
		return this.documentType;
	}

	/**
	 * Getter for "autor_typ_id" field (converted to DocumentAuthorType enum)
	 */
	public DocumentAuthorType getDocumentAuthorType() {
		return this.documentAuthorType;
	}
}
