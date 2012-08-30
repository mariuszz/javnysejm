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

import org.joda.time.LocalDateTime;
import org.junit.Test;

import pl.porannajava.javnysejm.Voting.DeputyVoting;
import pl.porannajava.javnysejm.Voting.VotingResult;

import com.google.common.collect.Table;

/**
 * Integration test for Voting class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class VotingIT {

	public VotingIT() {
		super();
	}

	/**
	 * Test URL: http://api.sejmometr.pl/glosowanie/277/info
	 */
	@Test
	public void getSingle() {
		Voting voting = new Voting.Builder(277).build();
		assertEquals(277, voting.getId());
		assertEquals("Przyjęcie poprawki 242.", voting.getTitle());
		assertEquals(7, voting.getSessionId());
		assertEquals(8875, voting.getDayId());
		assertEquals(205, voting.getItemId());
		assertEquals(269, voting.getConsiderationId());
		assertEquals(101, voting.getNumber());
		assertEquals(4416, voting.getSpeechId());
		assertEquals(new LocalDateTime(2012, 1, 27, 13, 32, 0),
				voting.getTime());
		assertEquals(VotingResult.NO, voting.getVotingResult());
		assertEquals(460, voting.getTotalCount());
		assertEquals(438, voting.getVotersCount());
		assertEquals(219, voting.getSupermajority());
		assertEquals(167, voting.getYesCount());
		assertEquals(269, voting.getNoCount());
		assertEquals(2, voting.getAbstentionCount());
		assertEquals(22, voting.getNotParticipantCount());
	}

	/**
	 * Test URL: http://api.sejmometr.pl/glosowanie/277/info,
	 * http://api.sejmometr.pl/glosowanie/277/wyniki
	 */
	@Test
	public void getSingleWithDetailedResults() {
		Voting voting = new Voting.Builder(277).withDetailedResults().build();
		Table<Integer, Integer, DeputyVoting> detailedResults = voting
				.getDetailedResults();
		assertEquals(DeputyVoting.NO,
				detailedResults.get(Integer.valueOf(120), Integer.valueOf(5)));
	}
}
