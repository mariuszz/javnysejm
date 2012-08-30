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

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Collection of small unit methods
 * 
 * @author Mariusz Zamolski
 * 
 */
public class TestUtils {

	/**
	 * Returns true, when bigArray contains all elements of smallArray
	 * 
	 * @param bigArray
	 *            - Integers array
	 * @param smallArray
	 *            - ints array
	 * @return boolean
	 */
	public static boolean idsArrayContains(int[] bigArray, int[] smallArray) {

		List<Integer> bigIntegers = Lists.newArrayList();
		for (int i : bigArray) {
			bigIntegers.add(Integer.valueOf(i));
		}
		List<Integer> smallIntegers = Lists.newArrayList();
		for (int i : smallArray) {
			smallIntegers.add(Integer.valueOf(i));
		}

		return bigIntegers.containsAll(smallIntegers);
	}
}
