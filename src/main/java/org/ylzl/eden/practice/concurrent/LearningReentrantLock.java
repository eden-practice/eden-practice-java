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

package org.ylzl.eden.practice.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrantlock 可重入锁例子
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningReentrantLock {

  private ReentrantLock lock = new ReentrantLock();

  private int value = 0;

  private static int staticValue = 0;

  private int count = 0;

  private final Condition notEmpty = lock.newCondition();

  /**
   * 显示锁，作用等同于 synchrionized 内置锁同步代码块
   *
   * @see LearningSynchronized#incrementAndGetWithBlock
   * @return
   */
  public int sameAsSynchrionized() {
    lock.lock();
    try {
      return ++value;
    } finally {
      lock.unlock();
    }
  }

  /**
   * 可重入，允许多次获取同一把锁
   *
   * @return
   */
  public int canReentrant() {
    lock.lock();
    try {
      lock.lock(); // 可重入，再次获取同一把锁
      return ++value;
    } finally {
      lock.unlock();
    }
  }

  /**
   * 尝试获取锁，如果等待获取锁超时，放弃，synchrionized 内置锁没有这个功能
   *
   * @return
   * @throws InterruptedException
   */
  public int tryLockMaybeGiveupIfTimeout() throws InterruptedException {
    lock.tryLock(10, TimeUnit.SECONDS);
    try {
      return ++value;
    } finally {
      lock.unlock();
    }
  }

  /**
   * 可中断，线程在等待获取锁的过程中可被中断
   *
   * @return
   * @throws InterruptedException
   */
  public void lockInterruptibly() throws InterruptedException {
    Thread thread =
        new Thread(
            new Runnable() {
              @SneakyThrows
              @Override
              public void run() {
                lock.lockInterruptibly();
                try {
                  for (int i = 0; ; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                      System.out.println("中断...");
                      return;
                    }
                    System.out.println("自旋...");
                  }
                } finally {
                  lock.unlock();
                }
              }
            });
    thread.start();
    Thread.sleep(1_000L);
    thread.interrupt();
  }

  /**
   * 等待队列，使用 Condition 的 await 方法阻塞线程，signal 方法唤醒线程
   *
   * @return
   * @throws InterruptedException
   */
  public void takeBlockingQueue() throws InterruptedException {
    new Thread(
            new Runnable() {
              @SneakyThrows
              @Override
              public void run() {
                lock.lockInterruptibly();
                try {
                  while (count == 0) {
                    Thread.sleep(500L);
                    System.out.println("队列为空，等待");
                    notEmpty.await();
                  }
                  System.out.println("队列不为空，执行");
                } finally {
                  lock.unlock();
                }
              }
            })
        .start();
    Thread.sleep(1_000L);
    lock.lockInterruptibly();
    try {
      count++;
      System.out.println("填充队列，唤醒");
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    LearningReentrantLock example = new LearningReentrantLock();
    example.lockInterruptibly();
  }
}
