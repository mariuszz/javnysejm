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
import java.util.Map.Entry;

import pl.porannajava.javnysejm.support.ReflectionUtils;

import com.google.common.base.Objects;

public abstract class AbstractEntity {

	@Override
	public String toString() {
		Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
		Map<String, String> toStringFields = ReflectionUtils
				.getObjectStringValues(this, this.hasId());
		for (Entry<String, String> entry : toStringFields.entrySet()) {
			toStringHelper.add(entry.getKey(), entry.getValue());
		}
		return toStringHelper.omitNullValues().toString();
	}

	protected abstract boolean hasId();

}
