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
 * Integration test for Session class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class SessionIT {
	public SessionIT() {
		super();
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posiedzenie/7/info
	 */
	@Test
	public void getSingle() {
		Session session = new Session.Builder(7).build();
		assertEquals(7, session.getId());
		assertEquals("7", session.getTitle());
		assertEquals(new LocalDate(2012, 1, 27), session.getStartDate());
		assertEquals(new LocalDate(2012, 1, 27), session.getEndDate());
		assertEquals(2, session.getPointsCount());
		assertEquals(200, session.getVotingsCount());
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posiedzenie/7/info Test URL:
	 * http://api.sejmometr.pl/posiedzenie/7/punkty
	 */
	@Test
	public void getSingleWithItems() {
		Session session = new Session.Builder(7).withItems().build();
		int[] testItems = new int[] { 205, 206 };
		assertTrue(TestUtils.idsArrayContains(session.getItems(), testItems));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posiedzenie/7/info Test URL:
	 * http://api.sejmometr.pl/posiedzenie/7/dni
	 */
	@Test
	public void getSingleWithDays() {
		Session session = new Session.Builder(7).withDays().build();
		int[] testItems = new int[] { 8875 };
		assertTrue(TestUtils.idsArrayContains(session.getDays(), testItems));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posiedzenie/7/info Test URL:
	 * http://api.sejmometr.pl/posiedzenie/7/rozpatrywania
	 */
	@Test
	public void getSingleWithConsiderations() {
		Session session = new Session.Builder(7).withConsiderations().build();
		int[] testItems = new int[] { 263, 264, 265, 266, 267, 268, 269, 270,
				271, 272, 273, 274 };
		assertTrue(TestUtils.idsArrayContains(session.getConsiderations(),
				testItems));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posiedzenie/7/info Test URL:
	 * http://api.sejmometr.pl/posiedzenie/7/dni
	 */
	@Test
	public void getSingleWithSpeeches() {
		Session session = new Session.Builder(7).withSpeeches().build();
		int[] testItems = new int[] { 4274, 4275, 4276, 4277, 4278, 4279, 4280,
				4281, 4282, 4283, 4284, 4285, 4286, 4287, 4288, 4289, 4290,
				4291, 4292, 4293, 4294, 4295, 4296, 4297, 4298, 4299, 4300,
				4301, 4302, 4303, 4304, 4305, 4306, 4307, 4308, 4309, 4310,
				4311, 4312, 4313, 4314, 4315, 4316, 4317, 4318, 4319, 4320,
				4321, 4322, 4323, 4324, 4325, 4326, 4327, 4328, 4329, 4330,
				4331, 4332, 4333, 4334, 4335, 4336, 4337, 4338, 4339, 4340,
				4341, 4342, 4343, 4344, 4345, 4346, 4347, 4348, 4349, 4350,
				4351, 4352, 4353, 4354, 4355, 4356, 4357, 4358, 4359, 4360,
				4361, 4362, 4363, 4364, 4365, 4366, 4367, 4368, 4369, 4370,
				4371, 4372, 4373, 4374, 4375, 4376, 4377, 4378, 4379, 4380,
				4381, 4382, 4383, 4384, 4385, 4386, 4387, 4388, 4389, 4390,
				4391, 4392, 4393, 4394, 4395, 4396, 4397, 4398, 4399, 4400,
				4401, 4402, 4403, 4404, 4405, 4406, 4407, 4408, 4409, 4410,
				4411, 4412, 4413, 4414, 4415, 4416, 4417, 4418, 4419, 4420,
				4421, 4422, 4423, 4424, 4425, 4426, 4427, 4428, 4429, 4430,
				4431, 4432, 4433, 4434, 4435, 4436, 4437, 4438, 4439, 4440,
				4441, 4442, 4443, 4444, 4445, 4446, 4447, 4448, 4449, 4450,
				4451, 4452, 4453, 4454, 4455, 4456, 4457, 4458, 4459, 4460,
				4461, 4462, 4463, 4464, 4465, 4466, 4467, 4468, 4469, 4470,
				4471, 4472, 4473, 4474, 4475, 4476, 4477, 4478, 4479, 4480,
				4481, 4482, 4483, 4484, 4485, 4486, 4487, 4488, 4489, 4490,
				4491, 4492, 4493, 4494, 4495, 4496, 4497, 4498, 4499, 4500,
				4501, 4502, 4503, 4504, 4505, 4506, 4507, 4508, 4509, 4510,
				4511, 4512, 4513, 4514, 4515, 4516, 4517, 4518, 4519, 4520,
				4521, 4522, 4523, 4524, 4525, 4526, 4527, 4528, 4529, 4530,
				4531, 4532, 4533, 4534, 4535, 4536, 4537, 4538, 4539, 4540,
				4541, 4542, 4543, 4544, 4545, 4546, 4547, 4548, 4549, 4550,
				4551, 4552, 4553, 4554, 4555, 4556, 4557, 4558, 4559, 4560,
				4561, 4562, 4563, 4564, 4565, 4566, 4567, 4568, 4569, 4570,
				4571, 4572, 4573, 4574, 4575, 4576, 4577, 4578, 4579, 4580,
				4581, 4582, 4583, 4584, 4585, 4586, 4587, 4588, 4589, 4590 };
		assertTrue(TestUtils.idsArrayContains(session.getSpeeches(), testItems));
	}

	/**
	 * Test URL: http://api.sejmometr.pl/posiedzenie/7/info Test URL:
	 * http://api.sejmometr.pl/posiedzenie/7/glosowania
	 */
	@Test
	public void getSingleWithVotings() {
		Session session = new Session.Builder(7).withVotings().build();
		int[] testItems = new int[] { 157, 158, 159, 160, 161, 162, 163, 164,
				165, 166, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198,
				199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210,
				211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222,
				223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234,
				235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246,
				247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258,
				259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270,
				271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282,
				283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294,
				295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306,
				307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318,
				319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330,
				331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342,
				343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354,
				355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366,
				367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378 };
		assertTrue(TestUtils.idsArrayContains(session.getVotings(), testItems));
	}

}
