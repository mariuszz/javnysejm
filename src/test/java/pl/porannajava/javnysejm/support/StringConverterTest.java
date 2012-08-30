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

import static junit.framework.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

/**
 * Unit test for StringConverter
 * 
 * @author Mariusz Zamolski
 * 
 */
public class StringConverterTest {

	@Test
	public void getUnescapedString() {
		assertEquals("", StringConverter.getUnescapedString(null));
		assertEquals("", StringConverter.getUnescapedString(""));
		assertEquals("",
				StringConverter.getUnescapedString("                      "));
		assertEquals("kazio", StringConverter.getUnescapedString("kazio"));
		assertEquals("poseł", StringConverter.getUnescapedString("pose\u0142"));
	}

	@Test
	public void getDate() {
		assertEquals(new LocalDate(2015, 4, 13),
				StringConverter.getDate("2015-04-13"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void getDateException() {
		assertEquals(new LocalDate(2015, 4, 13), StringConverter.getDate(null));
	}

	@Test
	public void getTime() {
		assertEquals(new LocalTime(22, 34, 56),
				StringConverter.getTime("22:34:56"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTimeException() {
		assertEquals(new LocalTime(22, 34, 56), StringConverter.getTime("sdf"));
	}

	@Test
	public void getDateTime() {
		assertEquals(new LocalDateTime(2015, 4, 13, 22, 34, 56),
				StringConverter.getDateTime("2015-04-13 22:34:56"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDateTimeException() {
		assertEquals(new LocalDateTime(2015, 4, 13, 22, 34, 56),
				StringConverter.getDateTime("2015-04- 22:34:56"));
	}

	@Test
	public void getInteger() {
		assertEquals(Integer.valueOf(123).intValue(), StringConverter.getInteger("123"));
	}

	@Test(expected = NumberFormatException.class)
	public void getIntegerException() {
		assertEquals(Integer.valueOf(123).intValue(), StringConverter.getInteger("1x2y3"));
	}

}
