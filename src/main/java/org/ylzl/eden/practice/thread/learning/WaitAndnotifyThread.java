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

package org.ylzl.eden.practice.thread.learning;

import lombok.SneakyThrows;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class WaitAndnotifyThread {

	private static Object lock = new Object();

	private static final Integer FULL = 10;

	private static Integer count = 0;

	static class Producer implements Runnable {

		@SneakyThrows
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock) {
					if (count == FULL) {
						lock.wait();
					}
					count++;
					System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
					lock.notifyAll();
				}
			}
		}
	}

	static class Consumer implements Runnable {

		@SneakyThrows
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock) {
					while (count == 0) {
						lock.wait();
					}
					count--;
					System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
					lock.notifyAll();
				}
			}
		}
	}

  public static void main(String[] args) {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
  }
}
