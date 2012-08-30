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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.joda.time.LocalTime;
import org.junit.Test;

/**
 * Integration test for Consideration class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class ConsiderationIT {
	public ConsiderationIT() {
		super();
	}

	/**
	 * Test URL: http://api.sejmometr.pl/rozpatrywanie/100/info
	 */
	@Test
	public void getSingle() {
		Consideration consideration = new Consideration.Builder(100).build();

		assertEquals(100, consideration.getId());
		assertEquals(3, consideration.getSessionId());
		assertEquals(18, consideration.getDayId());
		assertEquals(100, consideration.getItemId());
		assertEquals(
				"Punkt 5. porządku dziennego: Pierwsze czytanie rządowego projektu ustawy o zmianie ustawy o ewidencji ludności",
				consideration.getTitle());
		assertEquals(9, consideration.getSpeechesCount());
		assertEquals(0, consideration.getVotingsCount());
		assertEquals(new LocalTime(23, 34, 49), consideration.getTimeStart());
		assertEquals(new LocalTime(23, 54, 12), consideration.getTimeStop());
	}

	/**
	 * Test URL: http://api.sejmometr.pl/rozpatrywanie/100/info,
	 * http://api.sejmometr.pl/rozpatrywanie/100/wystapienia
	 */
	@Test
	public void getSingleWithSpeeches() {
		Consideration consideration = new Consideration.Builder(100)
				.withSpeeches().build();
		int[] testIds = new int[] { 1317, 1318, 1319, 1320, 1321, 1322, 1323,
				1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333,
				1334, 1335 };
		assertTrue(TestUtils.idsArrayContains(consideration.getSpeeches(),
				testIds));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/rozpatrywanie/43/info,
	 * http://api.sejmometr.pl/rozpatrywanie/43/glosowania
	 */
	@Test
	public void getSingleWithVotings() {
		Consideration consideration = new Consideration.Builder(43)
				.withVotings().build();
		int[] testIds = new int[] { 33, 34 };
		assertTrue(TestUtils.idsArrayContains(consideration.getVotings(),
				testIds));
	}
}