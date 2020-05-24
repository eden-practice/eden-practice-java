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

import org.ylzl.eden.practice.collections.Collection;
import org.ylzl.eden.practice.collections.Iterator;
import org.ylzl.eden.practice.concurrent.Delayed;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 延时队列
 *
 * @author gyl
 * @since 2.0.0
 */
public class DelayQueue<E extends Delayed> extends AbstractQueue<E> implements BlockingQueue<E> {

  private final transient ReentrantLock lock = new ReentrantLock();

  private final PriorityQueue<E> q = new PriorityQueue<E>();

  private Thread leader = null;

  private final Condition available = lock.newCondition();

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean offer(E e) {
    return false;
  }

  @Override
  public E poll() {
    return null;
  }

  @Override
  public E peek() {
    return null;
  }

  @Override
  public void put(E e) throws InterruptedException {}

  @Override
  public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
    return false;
  }

  @Override
  public E take() throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
      for (; ; ) {
        E first = q.peek();
        if (first == null) {
          available.await();
        } else {
          long delay = first.getDelay(NANOSECONDS);
          if (delay <= 0) {
            return q.poll();
          }
          first = null;
          if (leader != null) {
            available.await();
          } else {
            Thread thisThread = Thread.currentThread();
            leader = thisThread;
            try {
              available.awaitNanos(delay);
            } finally {
              if (leader == thisThread) {
              	leader = null;
			  }
            }
          }
        }
      }
    } finally {
      if (leader == null && q.peek() != null) {
      	available.signal();
	  }
      lock.unlock();
    }
  }

  @Override
  public E poll(long timeout, TimeUnit unit) throws InterruptedException {
    return null;
  }

  @Override
  public int remainingCapacity() {
    return 0;
  }

  @Override
  public int drainTo(Collection<? super E> c) {
    return 0;
  }

  @Override
  public int drainTo(Collection<? super E> c, int maxElements) {
    return 0;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }
}
