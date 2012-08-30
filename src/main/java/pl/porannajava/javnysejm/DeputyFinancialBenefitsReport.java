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

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import pl.porannajava.javnysejm.support.StringConverter;

/**
 * "Rejestr korzyści majątkowych posła" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class DeputyFinancialBenefitsReport extends AbstractInternalEntity {
	private LocalDate date;
	private String title;
	private int documentItemId;

	protected DeputyFinancialBenefitsReport(Map<String, String> properties) {
		this.date = StringConverter.getDate(properties.get("data"));
		this.title = StringConverter
				.getUnescapedString(properties.get("tytul"));
		this.documentItemId = StringConverter.getInteger(properties
				.get("dokument_id"));
	}

	/**
	 * Getter for "data" field
	 * 
	 * @return LocalDate
	 */
	public LocalDate getDate() {
		return this.date;
	}

	/**
	 * Getter for "tytul" field
	 * 
	 * @return String
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Getter for "dokument_id" field
	 * 
	 * @return String
	 */
	public int getDocumentItemId() {
		return this.documentItemId;
	}

	/**
	 * Builder for DeputyFinancialBenefitsReport entity
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public static class Builder extends
			InternalEntityListBuilder<DeputyFinancialBenefitsReport> {
		public Builder(int parentId, boolean notEmpty) {
			super("posel", "rejestr_korzysci", parentId, notEmpty);
		}

		@Override
		public DeputyFinancialBenefitsReport[] build() {
			List<DeputyFinancialBenefitsReport> list = this.getEntitiesList();
			return list.toArray(new DeputyFinancialBenefitsReport[list.size()]);
		}

		@Override
		protected DeputyFinancialBenefitsReport buildElement(
				Map<String, String> map) {
			return new DeputyFinancialBenefitsReport(map);
		}

	}

}
