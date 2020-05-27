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

package org.ylzl.eden.practice.collections;

import java.util.Arrays;

/**
 * 抽象 Collection
 *
 * @author gyl
 * @since 2.0.0
 */
public abstract class AbstractCustomCollection<E> implements CustomCollection<E> {

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  public abstract CustomIterator<E> iterator();

  public abstract int size();

  @Override
  public boolean isEmpty() {
    return size() == 0;
  } // 需要子类实现 size() 方法

  @Override
  public boolean contains(Object o) { // 使用 iterator() 遍历，复杂度为 O(n)
    CustomIterator<E> it = iterator();
    if (o == null) {
      while (it.hasNext()) {
        if (it.next() == null) {
          return true;
        }
      }
    } else {
      while (it.hasNext()) {
        if (o.equals(it.next())) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public Object[] toArray() { // 使用 iterator() 遍历，Arrays.copyOf 拷贝，调用私有方法 finishToArray()
    Object[] r = new Object[size()];
    CustomIterator<E> it = iterator();
    for (int i = 0; i < r.length; i++) {
      if (!it.hasNext()) {
        return Arrays.copyOf(r, i);
      }
      r[i] = it.next();
    }
    return it.hasNext() ? finishToArray(r, it) : r;
  }

  @Override
  public <T> T[] toArray(T[] a) { // 关键实现是 Arrays.copyOf 或者 System.arraycopy
    int size = size();
    T[] r =
        a.length >= size
            ? a
            : (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
    CustomIterator<E> it = iterator();

    for (int i = 0; i < r.length; i++) {
      if (!it.hasNext()) {
        if (a == r) {
          r[i] = null;
        } else if (a.length < i) {
          return Arrays.copyOf(r, i);
        } else {
          System.arraycopy(r, 0, a, 0, i);
          if (a.length > i) {
            a[i] = null;
          }
        }
        return a;
      }
      r[i] = (T) it.next();
    }
    return it.hasNext() ? finishToArray(r, it) : r;
  }

  @Override
  public boolean add(E e) { // 不支持新增元素，不代表子类不支持
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean remove(Object o) { // 调用了 Iterator 的 remove()
    CustomIterator<E> it = iterator();
    if (o == null) {
      while (it.hasNext()) {
        if (it.next() == null) {
          it.remove();
          return true;
        }
      }
    } else {
      while (it.hasNext()) {
        if (o.equals(it.next())) {
          it.remove();
          return true;
        }
      }
    }
    return false;
  }

  private static <T> T[] finishToArray(T[] r, CustomIterator<?> it) {
    int i = r.length;
    while (it.hasNext()) {
      int cap = r.length;
      if (i == cap) {
        int newCap = cap + (cap >> 1) + 1;
        if (newCap - MAX_ARRAY_SIZE > 0) {
          newCap = hugeCapacity(cap + 1);
        }
        r = Arrays.copyOf(r, newCap);
      }
      r[i++] = (T) it.next();
    }
    return (i == r.length) ? r : Arrays.copyOf(r, i);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) {
      throw new OutOfMemoryError("Required array size too large");
    }
    return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
  }

  @Override
  public boolean containsAll(CustomCollection<?> c) {
    return true;
  }

  @Override
  public boolean addAll(CustomCollection<? extends E> c) {
    return true;
  }

  @Override
  public boolean removeAll(CustomCollection<?> c) {
    return true;
  }

  @Override
  public boolean retainAll(CustomCollection<?> c) {
    return true;
  }
}
