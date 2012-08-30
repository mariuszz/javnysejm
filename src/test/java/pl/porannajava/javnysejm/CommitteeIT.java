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
 * Integration test for Committee class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class CommitteeIT {

	/**
	 * Test URL: http://api.sejmometr.pl/komisja/1/info
	 */
	@Test
	public void getSingle() {
		Committee committee = new Committee.Builder(1).build();

		assertEquals(1, committee.getId());
		assertEquals("Komisja Łączności z Polakami za Granicą",
				committee.getName());
		assertEquals("LPG", committee.getCode());
		assertEquals("tel.: (22) 694-19-56, 694-23-05", committee.getContact());
	}

}
