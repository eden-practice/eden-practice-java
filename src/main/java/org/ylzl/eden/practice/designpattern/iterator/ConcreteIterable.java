/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.practice.designpattern.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 具体迭代实现
 *
 * @author gyl
 * @since 2.0.0
 */
public class ConcreteIterable implements Iterable<Integer> {

	private Collection<Integer> elements = new ArrayList<>(); // 临时借用 JDK 保存

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return elements.iterator().hasNext();
			}

			@Override
			public Integer next() {
				return elements.iterator().next();
			}
		};
	}
}
