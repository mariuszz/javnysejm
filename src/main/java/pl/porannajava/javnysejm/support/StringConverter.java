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
package pl.porannajava.javnysejm.support;

import org.apache.commons.lang3.StringEscapeUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.google.common.base.Strings;

public class StringConverter {

	private static final DateTimeFormatter DATETIME_FORMATTER = new DateTimeFormatterBuilder()
			.appendPattern("yyyy-MM-dd HH:mm:ss").toFormatter();

	public static String getUnescapedString(String input) {
		return StringEscapeUtils.unescapeJava(normalizeString(input));
	}

	public static LocalDate getDate(String input) {
		return LocalDate.parse(normalizeString(input));
	}

	public static LocalTime getTime(String input) {
		return LocalTime.parse(normalizeString(input));
	}

	public static LocalDateTime getDateTime(String input) {

		return LocalDateTime.parse(normalizeString(input), DATETIME_FORMATTER);
	}

	public static int getInteger(String input) {
		return Integer.valueOf(normalizeString(input)).intValue();
	}

	private static String normalizeString(String input) {
		return Strings.nullToEmpty(input).trim();
	}

}
