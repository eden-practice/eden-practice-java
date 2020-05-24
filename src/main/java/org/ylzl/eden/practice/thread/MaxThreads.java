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
 * 测试最大线程数
 *
 * @author gyl
 * @since 2.0.0
 */
public class MaxThreads {

  private static Object lock = new Object();

  private static int count = 0;

  public static void main(String[] args) {
    for (; ; ) {
      new Thread(
              new Runnable() {
                public void run() {
                  synchronized (lock) {
                    count += 1;
                    System.out.println("当前线程数：" + count);
                  }
                  for (; ; ) {
                    try {
                      Thread.sleep(1000);
                    } catch (Exception e) {
                      System.err.println(e);
                    }
                  }
                }
              })
          .start();
    }
  }
}
