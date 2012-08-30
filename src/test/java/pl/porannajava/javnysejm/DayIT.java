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
import org.joda.time.LocalDateTime;
import org.junit.Test;

/**
 * Integration test for day class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class DayIT {

	public DayIT() {
		super();
	}

	/**
	 * Test URL: http://api.sejmometr.pl/dzien/36934/info
	 */
	@Test
	public void getSingle() {
		Day day = new Day.Builder(36934).build();
		assertEquals(36934, day.getId());
		assertEquals(13, day.getSessionId());
		assertEquals(new LocalDate(2012, 4, 27), day.getDate());
		assertEquals(13582, day.getDocumentItemId());
		assertEquals(new LocalDateTime(2012, 4, 27, 9, 6, 0),
				day.getTimeStart());
		assertEquals(new LocalDateTime(2012, 4, 27, 13, 54, 0),
				day.getTimeStop());
	}

	/**
	 * Test URL: http://api.sejmometr.pl/dzien/10272/info,
	 * http://api.sejmometr.pl/dzien/10272/rozpatrywania
	 */
	@Test
	public void getSingleWithConsiderations() {
		Day day = new Day.Builder(10272).withConsiderations().build();
		int[] testIds = new int[] { 318, 319, 320, 321, 322, 323, 324, 325,
				326, 327, 328, 329 };
		assertTrue(TestUtils.idsArrayContains(day.getConsiderations(), testIds));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/dzien/10272/info,
	 * http://api.sejmometr.pl/dzien/10272/wystapienia
	 */
	@Test
	public void getSingleWithSpeeches() {
		Day day = new Day.Builder(10272).withSpeeches().build();
		int[] testIds = new int[] { 5311, 5312, 5313, 5314, 5315, 5316, 5317,
				5318, 5319, 5320, 5321, 5322, 5323, 5324, 5325, 5326, 5327,
				5328, 5329, 5330, 5331, 5332, 5333, 5334, 5335, 5336, 5337,
				5338, 5339, 5340, 5341, 5342, 5343, 5344, 5345, 5346, 5347,
				5348, 5349, 5350, 5351, 5352, 5353, 5354, 5355, 5356, 5357,
				5358, 5359, 5360, 5361, 5362, 5363, 5364, 5365, 5366, 5367,
				5368, 5369, 5370, 5371, 5372, 5373, 5374, 5375, 5376, 5377,
				5378, 5379, 5380, 5381, 5382, 5383, 5384, 5385, 5386, 5387,
				5388, 5389, 5390, 5391, 5392, 5393, 5394, 5395, 5396, 5397,
				5398, 5399, 5400, 5401, 5402, 5403, 5404, 5405, 5406, 5407,
				5408, 5409, 5410, 5411, 5412, 5413, 5414, 5415, 5416, 5417,
				5418, 5419, 5420, 5421, 5422, 5423, 5424, 5425, 5426, 5427,
				5428, 5429, 5430, 5431, 5432, 5433, 5434, 5435, 5436, 5437,
				5438, 5439, 5440, 5441, 5442, 5443, 5444, 5445, 5446, 5447,
				5448, 5449, 5450, 5451, 5452, 5453, 5454, 5455, 5456, 5457,
				5458, 5459, 5460, 5461, 5462, 5463, 5464, 5465, 5466, 5467,
				5468, 5469, 5470, 5471, 5472, 5473, 5474, 5475, 5476, 5477,
				5478, 5479, 5480, 5481, 5482, 5483, 5484, 5485, 5486, 5487,
				5488, 5489, 5490, 5491, 5492, 5493, 5494, 5495, 5496, 5497,
				5498, 5499, 5500, 5501, 5502, 5503, 5504, 5505, 5506, 5507,
				5508, 5509, 5510, 5511, 5512, 5513, 5514, 5515, 5516, 5517,
				5518, 5519, 5520, 5521, 5522, 5523, 5524, 5525, 5526, 5527,
				5528, 5529, 5530, 5531, 5532, 5533, 5534, 5535, 5536, 5537,
				5538, 5539, 5540, 5541, 5542, 5543, 5544, 5545, 5546, 5547,
				5548, 5549, 5550, 5551, 5552, 5553, 5554, 5555, 5556, 5557,
				5558, 5559, 5560, 5561, 5562, 5563, 5564, 5565, 5566, 5567,
				5568, 5569, 5570, 5571, 5572, 5573, 5574, 5575, 5576, 5577,
				5578, 5579, 5580, 5581, 5582, 5583, 5584, 5585, 5586, 5587,
				5588, 5589, 5590, 5591 };
		assertTrue(TestUtils.idsArrayContains(day.getSpeeches(), testIds));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/dzien/10272/info,
	 * http://api.sejmometr.pl/dzien/10272/glosowania
	 */
	@Test
	public void getSingleWithVotings() {
		Day day = new Day.Builder(10272).withVotings().build();
		int[] testIds = new int[] { 384 };
		assertTrue(TestUtils.idsArrayContains(day.getVotings(), testIds));
	}

}
