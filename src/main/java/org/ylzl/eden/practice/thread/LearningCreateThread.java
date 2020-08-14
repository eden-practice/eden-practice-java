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

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningCreateThread {

  public static class ExtendsThread extends Thread {

    @Override
    public void run() {
      System.out.println("Created thread by extends Thread");
    }
  }

  public static class ImplementsRunnable implements Runnable {

    @Override
    public void run() {
      System.out.println("Created thread by implements Runnable");
    }
  }

  public static void createByThreadPool() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
    Future<String> future =
        executorService.submit(
            () -> {
            	Thread.sleep(2000L);
              System.out.println("233");
              return Thread.currentThread().getName();
            });
    System.out.println("123");
		executorService.shutdown();
    System.out.println(future.get());

    /*ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(
			() -> {
				while (true) {
					System.out.println(Thread.currentThread().getName() + " is running ..");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		executorService.shutdown();*/
	}

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    new ExtendsThread().start();
    new Thread(new ImplementsRunnable()).start();
		createByThreadPool();
  }
}
