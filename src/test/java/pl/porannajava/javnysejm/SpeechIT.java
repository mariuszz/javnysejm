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

/**
 * Integration test for Speech class
 * 
 * 
 * @author Mariusz Zamolski
 * 
 */
public class SpeechIT {

	public SpeechIT() {
		super();
	}

	/**
	 * Test URL: http://api.sejmometr.pl/wystapienie/7262/info
	 */
	@Test
	public void getSingle() {

		Speech speech = new Speech.Builder(7262).build();
		assertEquals(7262, speech.getId());
		assertEquals(10, speech.getSessionId());
		assertEquals(15865, speech.getDayId());
		assertEquals(406, speech.getItemId());
		assertEquals(406, speech.getConsiderationId());
		assertEquals(1, speech.getSpeakerFunctionId());
		assertEquals(788, speech.getSpeakerId());
		assertEquals(74, speech.getDeputyId());
		assertEquals(new LocalDateTime(2012, 3, 15, 21, 18, 01),
				speech.getTimeStart());
		assertEquals(new LocalDateTime(2012, 3, 15, 21, 19, 14),
				speech.getTimeStop());
		assertEquals(1, speech.getPositionId());
	}

	/**
	 * Test URL: http://api.sejmometr.pl/wystapienie/7262/info,
	 * http://api.sejmometr.pl/wystapienie/7262/tekst
	 */
	@Test
	public void getSingleWithText() {
		Speech speech = new Speech.Builder(7262).withText().build();
		assertEquals(
				"\"<p>Panie Marszałku!  Wysoka Izbo!  Panie Ministrze!  Ciemność po pana wystąpieniu widzę, wielką ciemność w pana resorcie, panie ministrze.  I widzę też taką wielką czarną dziurę, która pochłania po prostu wszystko</p><p class=\"opis\">(Wesołość na sali)</p><p>, wie pan, a przede wszystkim rozwój drogownictwa w Polsce.  To tak ogólnie, a teraz mam konkretne pytanie: Co dalej z obwodnicą Bełchatowa?  Miała być wybudowana 2–3 lata temu po zmianie przebiegu drogi ekspresowej S8 na wariant łódzki.  Niestety budowa do tej pory się nie rozpoczęła i nie wiadomo, kiedy się zacznie.  22-tysięczne Opoczno doczekało się obwodnicy w ciągu DK 12. </p><p>Otwarto ją w styczniu 2012 r.  A ponad 60-tysięczny Bełchatów nadal jest rozjeżdżany przez ciężarówki.  Kiedy zostanie rozpoczęta budowa drogi ekspresowej S12 Piotrków Trybunalski – Sulejów?  Mieszkańcy tego rejonu czekają na nią z wielką niecierpliwością, panie ministrze.  Panie ministrze, proszę wziąć się do roboty. </p><p>Dziękuję.</p><p class=\"opis\">(Oklaski)</p><p class=\"opis\">(Głos z sali: Brawo!)</p>\"",
				speech.getText());
	}
}
