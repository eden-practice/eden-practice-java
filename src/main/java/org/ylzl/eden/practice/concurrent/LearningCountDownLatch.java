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

package org.ylzl.eden.practice.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningCountDownLatch {

  public static void main(String[] args) {
    final CountDownLatch latch = new CountDownLatch(2);

    new Thread(() -> {
			try {
				System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
				Thread.sleep(1000);
				System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
				latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

    new Thread(() -> {
			try {
				System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
				Thread.sleep(2000);
				System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
				latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

    try {
      System.out.println("等待所有子线程执行完毕...");
      latch.await();
      System.out.println("所有子线程已经执行完毕");
      System.out.println("继续执行主线程");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
