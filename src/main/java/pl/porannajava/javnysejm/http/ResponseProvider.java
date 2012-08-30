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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.porannajava.javnysejm.support.JSException;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

public class ResponseProvider {

	private static final Logger logger = LoggerFactory
			.getLogger(ResponseProvider.class);

	public ResponseProvider() {
		super();
	}

	public Response getResponse(String urlString) {
		logger.info("requested url: " + urlString);
		Response responseToReturn = null;

		URL url;
		try {
			url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			byte[] bytes = ByteStreams.toByteArray(connection.getInputStream());

			responseToReturn = new Response(connection.getResponseCode(),
					new String(bytes, Charsets.UTF_8));
			connection.disconnect();
		} catch (MalformedURLException e) {
			throw new JSException("Wrong url", e);
		} catch (IOException e) {
			throw new JSException("Connection error", e);
		}

		return responseToReturn;
	}

}
