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

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * Integration test for Deputy class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class DeputyIT {

	/**
	 * Test URL: http://api.sejmometr.pl/posel/326/info
	 */
	@Test
	public void getSingle() {
		Deputy deputy = new Deputy.Builder(326).build();

		assertEquals(326, deputy.getId());
		assertEquals("adam-rogacki", deputy.getCode());
		assertEquals("Adam Rogacki", deputy.getFullName());
		assertEquals("Adam", deputy.getFirstName());
		assertEquals("Rogacki", deputy.getLastName());
		assertEquals(new LocalDate(1976, 2, 20), deputy.getBirthDate());
		assertEquals("Kalisz", deputy.getBirthPlace());
		assertEquals(2, deputy.getClubId());
		assertEquals(36, deputy.getElectoralDistrict());
		assertEquals(14903, deputy.getVotesNumber());
		assertEquals("prawnik", deputy.getProfession());
		assertEquals(0, deputy.getSpeeches().length);
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posel/326/info Test URL:
	 * http://api.sejmometr.pl/posel/326/wystapienia
	 */
	@Test
	public void getSingleWithSpeeches() {
		int[] testSpeeches = new int[] { 993, 1259, 1963, 2219, 2223, 2931,
				2937, 2968, 8296, 8308, 8330, 9031, 12428, 13629 };
		Deputy deputy = new Deputy.Builder(326).withSpeeches().build();
		assertTrue(TestUtils.idsArrayContains(deputy.getSpeeches(),
				testSpeeches));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posel/326/info Test URL:
	 * http://api.sejmometr.pl/posel/326/glosowania
	 */
	@Test
	public void getSingleWithVotings() {
		int[] testVotings = new int[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74,
				76, 77, 78, 82, 83, 84, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
				96, 97, 108 };
		Deputy deputy = new Deputy.Builder(326).withVotings().build();
		assertTrue(TestUtils.idsArrayContains(deputy.getVotings(), testVotings));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posel/326/info Test URL:
	 * http://api.sejmometr.pl/posel/326/komisje
	 */
	@Test
	public void getSingleWithDeputyCommittees() {
		Deputy deputy = new Deputy.Builder(326).withDeputyCommittees().build();
		assertEquals(3, deputy.getDeputyCommittees().length);
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posel/326/info Test URL:
	 * http://api.sejmometr.pl/posel/326/komisje
	 */
	@Test
	public void getSingleWithFinancialStatements() {
		Deputy deputy = new Deputy.Builder(326).withDeputyFinancialStatements()
				.build();
		assertEquals(2, deputy.getDeputyFinancialStatements().length);
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posel/230/info Test URL:
	 * http://api.sejmometr.pl/posel/230/rejestr_korzysci
	 */
	@Test
	public void getSingleWithFinancialBenefitsReports() {
		Deputy deputy = new Deputy.Builder(230)
				.withDeputyFinancialBenefitsReports().build();
		assertEquals(1, deputy.getDeputyFinancialBenefitsReports().length);
	}

}
