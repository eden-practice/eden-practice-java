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

import org.ylzl.eden.practice.collections.CustomCollection;

import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * @author gyl
 * @since 2.0.0
 */
public interface CustomBlockingQueue<E> extends CustomQueue<E> {

  void put(E e) throws InterruptedException;

  boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;

  E take() throws InterruptedException; // 检索并获取头部，等待元素返回

  E poll(long timeout, TimeUnit unit) throws InterruptedException;

  int remainingCapacity();

  boolean remove(Object o);

  public boolean contains(Object o);

  int drainTo(CustomCollection<? super E> c);

  int drainTo(CustomCollection<? super E> c, int maxElements);
}
