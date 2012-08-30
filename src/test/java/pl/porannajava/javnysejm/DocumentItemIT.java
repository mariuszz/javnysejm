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
import junit.framework.Assert;

import org.junit.Test;

/**
 * Integration test for DocumentItem class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class DocumentItemIT {

	/**
	 * Test URL: http://api.sejmometr.pl/dokument/2905/info
	 */
	@Test
	public void getSingle() {

		DocumentItem documentItem = new DocumentItem.Builder(2905).build();

		assertEquals(2905, documentItem.getId());
		assertEquals(
				"http://legislacja.rcl.gov.pl/docs//1/7084/7085/7086/dokument3433.pdf",
				documentItem.getUrl());
		assertEquals(87761886, documentItem.getScribdId());
		assertEquals("key-zh5uq8vem6ql5mct6aq",
				documentItem.getScribdAccessKey());
	}

	/**
	 * Test URL: http://api.sejmometr.pl/dokument/2905/info
	 */
	@Test
	public void getSingleWithText() {
		DocumentItem documentItem = new DocumentItem.Builder(2905).withText()
				.build();
		Assert.assertTrue(documentItem.getText().contains(
				"DO PROJEKTU USTAWY O MIĘDZYRESORTOWYM OPERATORZE SYSTEMU"));
	}
}
