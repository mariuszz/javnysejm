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

import org.junit.Test;

import pl.porannajava.javnysejm.Item.ItemDocumentAction;
import pl.porannajava.javnysejm.Item.ItemType;

/**
 * Integration test for Item class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class ItemIT {

	public ItemIT() {
		super();
	}

	/**
	 * Test URL: http://api.sejmometr.pl/punkt/199/info
	 */
	@Test
	public void getSingle() {
		Item item = new Item.Builder(199).build();
		assertEquals(199, item.getId());
		assertEquals("8.", item.getNumber());
		assertEquals(
				"Wniosek o wyrażenie wotum nieufności wobec ministra zdrowia Bartosza Arłukowicza",
				item.getTitle());
		assertEquals(6, item.getSessionId());
		assertEquals(ItemType.OTHER, item.getType());
		assertEquals(15523, item.getDocumentId());
		assertEquals(140, item.getVotingId());
		assertEquals(ItemDocumentAction.REJECTED, item.getItemDocumentAction());
	}

	/**
	 * Test URL: http://api.sejmometr.pl/punkt/406/info,
	 * http://api.sejmometr.pl/punkt/406/rozpatrywania
	 */
	@Test
	public void getSingleWithConsiderations() {
		Item item = new Item.Builder(406).withConsiderations().build();

		int[] testConsiderations = new int[] { 787, 814 };
		TestUtils
				.idsArrayContains(item.getConsiderations(), testConsiderations);

	}

	/**
	 * Test URL: http://api.sejmometr.pl/punkt/406/info,
	 * http://api.sejmometr.pl/punkt/406/druki
	 */
	@Test
	public void getSingleWithDocuments() {
		Item item = new Item.Builder(406).withDocuments().build();

		int[] testDocuments = new int[] { 977540, 977540, 1804923, 1804923,
				1892362 };
		TestUtils.idsArrayContains(item.getDocuments(), testDocuments);

	}
}
