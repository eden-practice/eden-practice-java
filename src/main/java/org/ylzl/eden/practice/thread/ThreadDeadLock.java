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

package org.ylzl.eden.practice.thread;

/**
 * 线程死锁
 *
 * @author gyl
 * @since 2.0.0
 */
public class ThreadDeadLock {

	public static void main(String[] args) {
		Thread t1 = new Thread(new DeadLockTest(true));
		Thread t2 = new Thread(new DeadLockTest(false));
		t1.setName("thread-dufy-1");
		t2.setName("thread-dufy-2");
		t1.start();
		t2.start();
	}

	static class DeadLockTest implements Runnable {

		private boolean flag;

		DeadLockTest(boolean flag) {
			this.flag = flag;
		}

		public void run() {

			if (flag) {
				while (true) {
					synchronized (Demo.o1) {
						System.out.println("o1 " + Thread.currentThread().getName());
						synchronized (Demo.o2) {
							System.out.println("o2 " + Thread.currentThread().getName());
						}
					}
				}
			} else {
				while (true) {
					synchronized (Demo.o2) {
						System.out.println("o2 " + Thread.currentThread().getName());
						synchronized (Demo.o1) {
							System.out.println("o1 " + Thread.currentThread().getName());
						}
					}
				}
			}
		}
	}

	static class Demo {
		static Object o1 = new Object();
		static Object o2 = new Object();
	}
}
