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


/**
 * "Funkcja mówcy" entity representation
 * 
 * @author Mariusz Zamolski
 * 
 */
public class SpeakerFunction extends IdEntity {

	private String name;

	protected SpeakerFunction(Builder builder) {
		super(builder);
		this.name = builder.getString("nazwa");
	}

	public static class Builder extends IdEntityBuilder<SpeakerFunction> {

		public Builder(int id) {
			super(id, "mowca_funkcja");
		}

		@Override
		public SpeakerFunction build() {
			return new SpeakerFunction(this);
		}

	}

	/**
	 * Getter for "nazwa" field
	 */
	public String getName() {
		return this.name;
	}

}
