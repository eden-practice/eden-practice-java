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

import java.util.concurrent.Semaphore;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningSemaphore {

  public static void main(String[] args) {
    int n = 8;
    Semaphore semaphore = new Semaphore(5);
    for (int i = 0; i < n; i++) {
      new Worker(i, semaphore).start();
    }
  }

  static class Worker extends Thread {
    private int num;
    private Semaphore semaphore;

    public Worker(int num, Semaphore semaphore) {
      this.num = num;
      this.semaphore = semaphore;
    }

    @Override
    public void run() {
      try {
        semaphore.acquire();
        System.out.println("工人" + this.num + "占用一个机器在生产...");
        Thread.sleep(2000);
        System.out.println("工人" + this.num + "释放出机器");
        semaphore.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
