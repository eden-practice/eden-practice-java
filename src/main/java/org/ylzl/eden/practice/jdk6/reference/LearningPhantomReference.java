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

package org.ylzl.eden.practice.jdk6.reference;

import org.ylzl.eden.practice.collections.list.ArrayList;
import org.ylzl.eden.practice.collections.list.List;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 虚引用
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningPhantomReference {

	static class Test {

	}

	// -Xmx10m
	public static void main(String[] args) {
		ReferenceQueue queue = new ReferenceQueue();
		Test test = new Test();
		PhantomReference<Test> reference = new PhantomReference<Test>(test, queue);
		new Thread(() -> {
			boolean flag = true;
			while (flag) {
				Reference refer = queue.poll();
				if (refer != null) {
					System.out.println("虚引用队列：" + refer);
					flag = false;
				}
			}
		}).start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test = null;
		System.gc();
	}
}
