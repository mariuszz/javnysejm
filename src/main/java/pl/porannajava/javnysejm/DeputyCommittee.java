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

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import pl.porannajava.javnysejm.support.StringConverter;

/**
 * "Komisja posła" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class DeputyCommittee extends AbstractInternalEntity {
	private int committeeId;
	private Interval interval;
	private String function;

	protected DeputyCommittee(Map<String, String> properties) {
		this.committeeId = StringConverter.getInteger(properties
				.get("komisja_id"));

		LocalDate toDate = null;
		try {
			toDate = StringConverter.getDate(properties.get("do"));
		} catch (IllegalFieldValueException e) {
			// nothing to do.
			// trying to catch "0000-00-00" date
		}
		// the last day on Earth :)
		DateTime toDateTime = new DateTime(Long.MAX_VALUE);
		if (toDate != null) {
			toDateTime = toDate.toDateTimeAtStartOfDay();
		}
		this.interval = new Interval(StringConverter.getDate(
				properties.get("od")).toDateTimeAtStartOfDay(), toDateTime);
		this.function = StringConverter.getUnescapedString(properties
				.get("funkcja"));
	}

	public int getCommitteeId() {
		return this.committeeId;
	}

	public Interval getInterval() {
		return this.interval;
	}

	public String getFunction() {
		return this.function;
	}

	/**
	 * Builder for DeputyCommittee entity
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public static class Builder extends
			InternalEntityListBuilder<DeputyCommittee> {
		public Builder(int parentId, boolean notEmpty) {
			super("posel", "komisje", parentId, notEmpty);
		}

		@Override
		public DeputyCommittee[] build() {
			List<DeputyCommittee> list = this.getEntitiesList();
			return list.toArray(new DeputyCommittee[list.size()]);
		}

		@Override
		protected DeputyCommittee buildElement(Map<String, String> map) {
			return new DeputyCommittee(map);
		}

	}
}
