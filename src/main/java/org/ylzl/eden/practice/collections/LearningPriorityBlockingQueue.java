/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
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

package org.ylzl.eden.practice.collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningPriorityBlockingQueue {

	private final PriorityBlockingQueue<Person> queue = new PriorityBlockingQueue();

	@AllArgsConstructor
	@Builder
	@Data
	static class Person implements Comparable<Person> {

		private String name;

		private Integer age;

		@Override
		public int compareTo(Person o) {
			if (age > o.age) {
				return -1;
			}
			return 1;
		}
	}

  public static void main(String[] args) throws InterruptedException {
		LearningPriorityBlockingQueue learning = new LearningPriorityBlockingQueue();
		learning.queue.add(Person.builder().name("A").age(23).build());
		learning.queue.add(Person.builder().name("B").age(17).build());
		learning.queue.add(Person.builder().name("C").age(21).build());
		learning.queue.add(Person.builder().name("D").age(15).build());
		learning.queue.add(Person.builder().name("E").age(13).build());
		while (!learning.queue.isEmpty()) {
			try {
				System.out.println(learning.queue.take().age);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
  }
}
