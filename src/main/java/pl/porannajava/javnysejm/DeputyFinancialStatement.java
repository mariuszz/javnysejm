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
 * "Oświadczenia majątkowe posła" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class DeputyFinancialStatement extends AbstractInternalEntity {
	private LocalDate date;
	private int documentItemId;

	protected DeputyFinancialStatement(Map<String, String> properties) {
		this.date = StringConverter.getDate(properties.get("data"));
		this.documentItemId = StringConverter.getInteger(properties
				.get("dokument_id"));
	}

	/**
	 * Getter for "data" field
	 * 
	 */
	public LocalDate getDate() {
		return this.date;
	}

	/**
	 * Getter for "dokument_id" field
	 * 
	 */
	public int getDocumentItemId() {
		return this.documentItemId;
	}

	/**
	 * Builder for DeputyFinancialStatement entity
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public static class Builder extends
			InternalEntityListBuilder<DeputyFinancialStatement> {
		public Builder(int parentId, boolean notEmpty) {
			super("posel", "oswiadczenia_majatkowe", parentId, notEmpty);
		}

		@Override
		public DeputyFinancialStatement[] build() {
			List<DeputyFinancialStatement> list = this.getEntitiesList();
			return list.toArray(new DeputyFinancialStatement[list.size()]);
		}

		@Override
		protected DeputyFinancialStatement buildElement(Map<String, String> map) {
			return new DeputyFinancialStatement(map);
		}

	}
}