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

/**
 * Integration test for SpeakerFunction class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class SpeakerFunctionIT {

	public SpeakerFunctionIT() {
		super();
	}

	/**
	 * Test URL: http://api.sejmometr.pl/mowca_funkcja/44/info
	 */
	@Test
	public void getSingle() {
		SpeakerFunction speakerFunction = new SpeakerFunction.Builder(44)
				.build();

		assertEquals(44, speakerFunction.getId());
		assertEquals("Minister Spraw Wewnętrznych i Administracji",
				speakerFunction.getName());
	}
}
