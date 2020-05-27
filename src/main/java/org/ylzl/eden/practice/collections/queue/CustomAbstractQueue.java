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

import org.ylzl.eden.practice.collections.AbstractCustomCollection;
import org.ylzl.eden.practice.collections.CustomCollection;

import java.util.NoSuchElementException;

/**
 * 抽象队列
 *
 * @author gyl
 * @since 2.0.0
 */
public abstract class CustomAbstractQueue<E> extends AbstractCustomCollection<E> implements CustomQueue<E> {

  public boolean add(E e) {
    if (offer(e)) {
      return true;
    }
    throw new IllegalStateException("Queue full");
  }

  public E remove() {
    E x = poll();
    if (x != null) {
      return x;
    }
    throw new NoSuchElementException();
  }

  public E element() {
    E x = peek();
    if (x != null) {
      return x;
    }
    throw new NoSuchElementException();
  }

  public void clear() {
    while (poll() != null) ;
  }

  public boolean addAll(CustomCollection<? extends E> c) {
    return false;
  }
}
