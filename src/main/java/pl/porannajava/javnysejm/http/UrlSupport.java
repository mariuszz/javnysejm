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

package pl.porannajava.javnysejm.http;

public class UrlSupport {

	private static final String BASE_URL = "http://api.sejmometr.pl/";

	public static Response getListUrlResponse(final String type) {
		return getResponse(BASE_URL + type);
	}

	public static Response getInfoUrlResponse(final String type, final long id) {
		return UrlSupport.getUrlResponse(type, "info", id);
	}

	public static Response getUrlResponse(final String type, final String mode,
			final long id) {
		return getResponse(BASE_URL + type + "/" + id + "/" + mode);
	}

	private static Response getResponse(final String url) {
		return new ResponseProvider().getResponse(url);
	}

}
