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

package org.ylzl.eden.practice.collections.list;

import java.io.Serializable;
import java.util.Arrays;
import java.util.RandomAccess;

/**
 * 顺序表 List
 *
 * @author gyl
 * @since 2.0.0
 */
public class AbstractArrayList<E> extends ListAbstract<E>
    implements List<E>, RandomAccess, Cloneable, Serializable {

  private static final int DEFAULT_CAPACITY = 10; // 默认初始化容量为 10

  transient Object[] elementData; // 使用对象数组保存元素

  private int size; // 已添加的元素个数

  protected transient int modCount = 0; // 修改次数

  private static final Object[] EMPTY_ELEMENTDATA = {};

  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  @Override
  public E get(int index) { // 直接从数组下标获取，复杂度为 0(1)
    // rangeCheck(index);
    return elementData(index);
  }

  @Override
  public boolean add(E e) {
    ensureCapacityInternal(size + 1); // 判断是否扩充数组
    elementData[size++] = e;
    return true;
  }

  @Override
  public void clear() { // 清空不会缩小原来的长度
    modCount++;

    for (int i = 0; i < size; i++) {
      elementData[i] = null;
    }

    size = 0;
  }

  E elementData(int index) {
    return (E) elementData[index];
  }

  private void ensureCapacityInternal(int minCapacity) {
    ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
  }

  private void ensureExplicitCapacity(int minCapacity) {
    modCount++;

    if (minCapacity - elementData.length > 0) { // 如果最低容量超过当前数组的长度，扩充
      grow(minCapacity);
    }
  }

  private static int calculateCapacity(Object[] elementData, int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
      return Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    return minCapacity;
  }

  private void grow(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); // 默认扩充长度为当前数组的长度 1.5 倍
    if (newCapacity - minCapacity < 0) {
      newCapacity = minCapacity;
    }
    if (newCapacity - MAX_ARRAY_SIZE > 0) {
      newCapacity = hugeCapacity(minCapacity);
    }
    elementData = Arrays.copyOf(elementData, newCapacity); // 数组拷贝
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) {
      throw new OutOfMemoryError();
    }
    return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
  }
}
