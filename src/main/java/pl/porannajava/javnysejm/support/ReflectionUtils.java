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

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

public class ReflectionUtils {
	final static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	public static Map<String, String> getObjectStringValues(Object object,
			boolean withId) {
		ImmutableMap.Builder<String, String> mapBuilder = new ImmutableMap.Builder<>();
		if (withId) {
			// add id field
			mapBuilder.put("id", getIdFieldStringValue(object));
		}
		for (Field field : object.getClass().getDeclaredFields()) {

			field.setAccessible(true);
			try {
				Object objectValue = field.get(object);

				String stringValue = "null";
				if (objectValue != null) {
					if (objectValue.getClass().equals(int[].class)) {
						stringValue = getIntsArrayStringValue(objectValue);
					} else if (objectValue.getClass().isArray()) {
						stringValue = getArrayStringValue(objectValue);
					} else {
						stringValue = objectValue.toString();
					}
				}
				mapBuilder.put(field.getName(), stringValue);
			} catch (IllegalArgumentException e) {
				logger.error("field access error", e);
			} catch (IllegalAccessException e) {
				logger.error("field access error", e);
			}
		}

		return mapBuilder.build();
	}

	private static String getIntsArrayStringValue(Object object) {
		StringBuilder builder = new StringBuilder("[");
		if (object != null) {
			int[] ints = (int[]) object;
			for (int i = 0; i < ints.length; i++) {
				builder.append(ints[i]);
				if (i < (ints.length - 1)) {
					builder.append(", ");
				}
			}
		}
		builder.append("]");

		return builder.toString();
	}
	
	
	private static String getArrayStringValue(Object object) {
		StringBuilder builder = new StringBuilder("[");
		if (object != null) {
			Object[] objects = (Object[]) object;
			for (int i = 0; i < objects.length; i++) {
				builder.append(objects[i]);
				if (i < (objects.length - 1)) {
					builder.append(", ");
				}
			}
		}
		builder.append("]");

		return builder.toString();
	}

	private static String getIdFieldStringValue(Object object) {
		String stringValue = null;
		try {
			Field field = object.getClass().getSuperclass()
					.getDeclaredField("id");
			field.setAccessible(true);
			Object objectValue = field.get(object);
			if (objectValue != null) {
				stringValue = objectValue.toString();
			}
		} catch (SecurityException e) {
			throw new JSException("reflection problem", e);
		} catch (NoSuchFieldException e) {
			throw new JSException("reflection problem", e);
		} catch (IllegalArgumentException e) {
			throw new JSException("reflection problem", e);
		} catch (IllegalAccessException e) {
			throw new JSException("reflection problem", e);
		}

		return stringValue;

	}
}
