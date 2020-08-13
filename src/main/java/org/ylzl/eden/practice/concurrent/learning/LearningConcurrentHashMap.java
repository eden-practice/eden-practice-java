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

package org.ylzl.eden.practice.concurrent.learning;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningConcurrentHashMap {

	public static void main(String[] args) throws InterruptedException {
		AtomicInteger count = new AtomicInteger();
		ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<String, AtomicInteger>();
		map.put("key", count);
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 1000; i++) {
			executorService.execute(() -> {
					map.get("key").getAndIncrement();
					map.put("key", map.get("key"));
			});
		}
		Thread.sleep(1000);
		System.out.println(map.get("key"));
		executorService.shutdown();
	}
}
