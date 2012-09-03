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

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import pl.porannajava.javnysejm.http.Response;
import pl.porannajava.javnysejm.http.UrlSupport;
import pl.porannajava.javnysejm.support.JSException;
import pl.porannajava.javnysejm.support.JsonSupport;
import pl.porannajava.javnysejm.support.StringConverter;

public abstract class IdEntityBuilder<T extends IdEntity> {

	protected int id;
	private String name;
	protected Map<String, String> properties;

	protected IdEntityBuilder(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.properties = JsonSupport.getStringsMap(UrlSupport
				.getInfoUrlResponse(this.name, this.id).getBody());
		if (this.properties == null) {
			throw new JSException("error while parsing properties for id:" + id
					+ ", name=" + name);
		}
	}

	protected abstract T build();

	private String getPropertyValue(String code) {
		return this.getPropertyValue(code, true);
	}

	private String getPropertyValue(String code, boolean required) {
		String value = this.properties.get(code);
		if ((value == null) && required) {
			throw new JSException("property: " + code + " not found");
		}
		return value;
	}

	protected String getString(String code) {
		return StringConverter.getUnescapedString(this.getPropertyValue(code));
	}

	protected LocalDate getDate(String code) throws JSException {
		LocalDate date = null;
		try {
			date = StringConverter.getDate(this.getPropertyValue(code));
		} catch (IllegalArgumentException e) {
			throw new JSException(e);
		}
		return date;
	}

	protected LocalDateTime getDateTime(String code, boolean required) {
		LocalDateTime dateTime = null;
		try {
			String stringValue = this.getPropertyValue(code, required);
			if (stringValue != null) {
				dateTime = StringConverter.getDateTime(stringValue);
			}
		} catch (IllegalArgumentException e) {
			if (required) {
				throw new JSException(e);
			}
		}
		return dateTime;
	}

	protected LocalTime getTime(String code) {
		LocalTime time = null;
		try {
			time = StringConverter.getTime(this.getPropertyValue(code));
		} catch (IllegalArgumentException e) {
			throw new JSException(e);
		}
		return time;
	}

	protected int getInt(String code) {
		return this.getInt(code, true);
	}

	protected int getInt(String code, boolean required) {
		int intToReturn = 0;
		try {
			intToReturn = StringConverter.getInteger(this.getPropertyValue(
					code, required));
		} catch (NumberFormatException e) {
			if (required) {
				throw new JSException(e);
			}
		}
		return intToReturn;
	}

	protected int[] getIds(String type, String mode, int idParam,
			boolean notEmpty) {

		int[] array = new int[] {};

		if (notEmpty) {
			Response response = UrlSupport.getUrlResponse(type, mode, idParam);
			array = JsonSupport.getIds(response.getBody());
		}
		return array;
	}

}
