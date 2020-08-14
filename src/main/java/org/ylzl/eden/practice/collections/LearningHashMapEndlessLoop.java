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

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * HashMap 死循环
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningHashMapEndlessLoop {

	public static void main(String[] args) throws InterruptedException {
		final HashMap<String, String> map = new HashMap<>();
		int num = 100;
		final CountDownLatch countDownLatch = new CountDownLatch(num);
		for (int i = 0; i < num; i++) {
			final Integer integer = i;
			new Thread(() -> {
				for (int j = 0; j < 100; j++) {
					map.put("a" + integer + j, "b" + integer + j);
				}
				countDownLatch.countDown();
			}).start();
		}
		countDownLatch.await();
		System.out.println(map.size());
		// 循环读取 map 中不存在的 key，如果生成了环形链表，当 key 的 hash 值匹配到该链表时，发生死循环
		for (int m = 0; m < 1000; m++) {
			System.out.println("c" + m + ":  " + map.get("c" + m));
		}
	}
}
