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
import java.util.Map;

import pl.porannajava.javnysejm.http.Response;
import pl.porannajava.javnysejm.http.UrlSupport;
import pl.porannajava.javnysejm.support.JsonSupport;

import com.google.common.collect.Lists;

public abstract class InternalEntityListBuilder<T> {
	private String type;
	private String mode;
	private int parentId;
	private boolean notEmpty;

	public InternalEntityListBuilder(String type, String mode, int parentId,
			boolean notEmpty) {
		super();
		this.type = type;
		this.mode = mode;
		this.parentId = parentId;
		this.notEmpty = notEmpty;
	}

	private Map<String, String>[] getPropertiesArray() {
		Response response = UrlSupport.getUrlResponse(this.type, this.mode,
				this.parentId);
		return JsonSupport.getPropertiesArray(response.getBody());
	}

	protected List<T> getEntitiesList() {
		List<T> tempList = Lists.newArrayListWithCapacity(0);
		if (this.notEmpty) {
			for (Map<String, String> map : this.getPropertiesArray()) {
				tempList.add(this.buildElement(map));
			}
		}
		return tempList;
	}

	public abstract T[] build();

	protected abstract T buildElement(Map<String, String> map);

}
