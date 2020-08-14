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

package org.ylzl.eden.practice.jvm;

/**
 * JIT 栈上内存分配
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningJitStackAlloc {

	// JVM args：-Xmx4G -Xms4G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
	// jps -l -> jmap -histo
	public static void main(String[] args) {
		long a1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			alloc();
		}
		long a2 = System.currentTimeMillis();
		System.out.println("cost " + (a2 - a1) + " ms");
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private static void alloc() {
		User user = new User();
	}

	static class User {

	}
}
