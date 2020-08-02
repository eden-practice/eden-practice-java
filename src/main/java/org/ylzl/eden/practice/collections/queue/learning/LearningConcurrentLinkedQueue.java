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

package org.ylzl.eden.practice.collections.queue.learning;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningConcurrentLinkedQueue {

	private ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();

  public static void main(String[] args) {
		LearningConcurrentLinkedQueue learning = new LearningConcurrentLinkedQueue();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		try {
			executorService.submit(() -> {
				for (int i = 0; i < 100000; i++) {
					learning.queue.add(i);
				}
			});
			executorService.submit(() -> {
				for (int i = 0; i < 100000; i++) {
					learning.queue.poll();
				}
			});
		} finally {
			executorService.shutdown();
		}
	}
}
