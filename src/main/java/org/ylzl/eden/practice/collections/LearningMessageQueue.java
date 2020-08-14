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

package org.ylzl.eden.practice.collections;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningMessageQueue {

  private final LinkedList<String> queues = Lists.newLinkedList();

  private final int min = 0;

  private final int max;

  private final AtomicInteger count = new AtomicInteger();

  private final Object lock = new Object();

  public LearningMessageQueue(int max) {
    this.max = max;
  }

  public void produce(String message) {
    synchronized (lock) {
      while (count.get() == max) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      queues.add(message);
      count.incrementAndGet();
      lock.notify();
      System.out.println(Thread.currentThread().getName() + " produce message: " + message);
    }
  }

  public String consume() {
    synchronized (lock) {
      while (count.get() == min) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      String message = queues.removeFirst();
      count.decrementAndGet();
      lock.notify();
      System.out.println(Thread.currentThread().getName() + " consume message: " + message);
      return message;
    }
  }

  public static void main(String[] args) {
    LearningMessageQueue mq = new LearningMessageQueue(20);
    ExecutorService executorService = Executors.newFixedThreadPool(6);
    try {
      executorService.submit(
          () -> {
            for (int i = 1; i <= 20; i++) {
              int finalI = i;
              mq.produce("消息" + finalI);
            }
          });
      for (int i = 1; i <= 5; i++) {
        executorService.submit(
            () -> {
              mq.consume();
            });
      }
    } finally {
      executorService.shutdown();
    }
  }
}
