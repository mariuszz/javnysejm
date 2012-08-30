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

import pl.porannajava.javnysejm.http.UrlSupport;
import pl.porannajava.javnysejm.support.StringConverter;

/**
 * "Dokument" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class DocumentItem extends IdEntity {

	private String url;
	private int scribdId;
	private String scribdAccessKey;

	private String text = "";

	protected DocumentItem(Builder builder) {
		super(builder);
		this.url = builder.getString("url");
		this.scribdId = builder.getInt("scribd_id");
		this.scribdAccessKey = builder.getString("scribd_access_key");
		if (builder.withText) {
			this.text = StringConverter.getUnescapedString(UrlSupport
					.getUrlResponse("dokument", "tekst", this.id).getBody());
		}
	}

	/**
	 * Builder for DocumentItem entity
	 * 
	 * @author Mariusz Zamolski
	 * 
	 */
	public static class Builder extends IdEntityBuilder<DocumentItem> {

		protected boolean withText;

		public Builder(int id) {
			super(id, "dokument");
		}

		public Builder withText() {
			this.withText = true;
			return this;
		}

		@Override
		public DocumentItem build() {
			return new DocumentItem(this);
		}
	}

	/**
	 * Getter for "url" field
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Getter for "nazwa" field
	 */
	public int getScribdId() {
		return this.scribdId;
	}

	/**
	 * Getter for "nazwa" field
	 */
	public String getScribdAccessKey() {
		return this.scribdAccessKey;
	}

	/**
	 * Getter for text e.g. http://api.sejmometr.pl/dokument/2905/tekst
	 */
	public String getText() {
		return this.text;
	}
}
