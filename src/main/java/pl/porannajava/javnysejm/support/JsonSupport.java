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

package pl.porannajava.javnysejm.support;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonSupport {

	public static int[] getIds(String string) {
		Gson gson = new Gson();
		return gson.fromJson(string, int[].class);
	}

	public static Map<String, String> getStringsMap(String string) {
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Map<String, String>>() {
			// nothing
		}.getType();
		return gson.fromJson(string, collectionType);
	}

	public static Map<String, String>[] getPropertiesArray(String string) {
		Gson gson = new Gson();
		Type collectionType = new TypeToken<Map<String, String>[]>() {
			// nothing
		}.getType();
		return gson.fromJson(string, collectionType);

	}
}
