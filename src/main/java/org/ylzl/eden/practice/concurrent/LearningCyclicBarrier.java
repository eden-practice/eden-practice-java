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

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningCyclicBarrier {

  public static void main(String[] args) {
    int n = 3;
    CyclicBarrier barrier =
        new CyclicBarrier(
            n,
            () -> {
              System.out.println("选择" + Thread.currentThread().getName() + "回调执行");
            });
    for (int i = 0; i < n; i++) {
      new Test(barrier).start();
    }

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < n; i++) {
      new Test(barrier).start();
    }
  }

  static class Test extends Thread {

    private CyclicBarrier cyclicBarrier;

    public Test(CyclicBarrier cyclicBarrier) {
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
      try {
        Thread.sleep(2000); // 以睡眠来模拟写入数据操作
        System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      System.out.println("所有线程写入完毕，继续处理其他任务...");
    }
  }
}
