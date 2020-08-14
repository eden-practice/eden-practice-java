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
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningJoinThread {

  public static void main(String[] args) throws InterruptedException {
    Thread t1 =
        new Thread(
            () -> {
              System.out.println("线程 1 开始执行");
              for (int i = 0; i < 10; i++) {
                System.out.println("线程 1 执行：" + i);
              }
              System.out.println("线程 1 结束执行");
            });
    t1.start();

		System.out.println("主线程开始执行");
    for (int i = 0; i < 10; i++) {
      System.out.println("主线程执行：" + i);
      if (i >= 2) {
        try {
          // t1 线程合并到主线程中，主线程停止执行过程，转而执行t1线程，直到t1执行完毕后继续；
          t1.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
