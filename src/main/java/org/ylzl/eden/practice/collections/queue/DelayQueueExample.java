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

package org.ylzl.eden.practice.collections.queue;

import org.ylzl.eden.practice.concurrent.LearningDelayed;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class DelayQueueExample {

  public static void main(String[] args) {
    DelayQueue<LearningDelayed> queue = new DelayQueue();
    for (int i = 1; i <= 10; i++) {
      LearningDelayed learningDelayed = new LearningDelayed("订单信息" + i, i * 10);
      System.out.println("新增订单：" + learningDelayed);
      queue.add(learningDelayed);
    }

    ExecutorService executorService =
        Executors.newSingleThreadExecutor(
            r -> {
              Thread thread = new Thread(r);
              thread.setName("DelayWorker");
              thread.setDaemon(false);
              return thread;
            });

    executorService.execute(
        () -> {
          while (true) {
            LearningDelayed task = null;
            try {
              task = queue.take();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("延迟处理订单消息：" + task);
          }
        });
  }
}
