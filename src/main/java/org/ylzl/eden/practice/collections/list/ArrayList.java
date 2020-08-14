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

package org.ylzl.eden.practice.collections.list;

import lombok.ToString;
import org.ylzl.eden.practice.collections.iterator.Iterator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.RandomAccess;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
@ToString
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, Serializable {

  private transient Object[] elementDatas;

  private int size;

  private int modCount;

  private static final int DEFAULT_CAPACITY = 10; // 默认初始化容量为 10

  private static final Object[] EMPTY_ELEMENTDATA = {};

  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  public ArrayList() {
    elementDatas = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
  }

  public ArrayList(int capacity) {
    if (capacity > 0) {
      elementDatas = new Object[capacity];
    } else if (capacity == 0) {
      elementDatas = EMPTY_ELEMENTDATA;
    } else {
      throw new IllegalArgumentException("容量必须为正数，capacity：" + capacity);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {

      private int cursor;

      @Override
      public boolean hasNext() {
        return cursor != 0 && cursor < elementDatas.length;
      }

      @Override
      public E next() {
        cursor++;
        return (E) elementDatas[cursor];
      }

      @Override
      public void remove() {}
    };
  }

  @Override
  public E get(int index) {
    rangeCheck(index);
    return (E) elementDatas[index];
  }

  @Override
  public boolean add(E element) {
    ensureCapacityInternal(size + 1);
    elementDatas[size++] = element;
		modCount++;
    return true;
  }

	@Override
	public void add(int index, E element) {
		ensureCapacityInternal(size + 1);
		System.arraycopy(elementDatas, index, elementDatas, index + 1, size - index);
		elementDatas[index] = element;
		size++;
		modCount++;
	}

	@Override
  public void clear() {
    for (int i = 0; i < elementDatas.length; i++) {
			elementDatas[i] = null;
		}
  }

  private void ensureCapacityInternal(int minCapacity) {
		if (elementDatas == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
    if (minCapacity > elementDatas.length) {
			grow(minCapacity);
    }
  }

  private void grow(int minCapacity) {
		int oldCapacity = elementDatas.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity < minCapacity) {
      newCapacity = minCapacity;
    }
		elementDatas = Arrays.copyOf(elementDatas, newCapacity);
	}

  private void rangeCheck(int index) {
    if (index >= size) {
      throw new IndexOutOfBoundsException("索引越界，index：" + index);
    }
  }

	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList();
		arrayList.add(5);
		arrayList.add(2);
		arrayList.add(0);
		arrayList.add(3, 233);
    System.out.println(arrayList);
		arrayList.clear();
    System.out.println(arrayList);
  }
}
