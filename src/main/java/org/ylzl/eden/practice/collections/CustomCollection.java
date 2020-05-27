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

/**
 * Collection 接口
 *
 * @author gyl
 * @since 2.0.0
 */
public interface CustomCollection<E> extends CustomIterable<E> {

  int size(); // 返回元素大小

  boolean isEmpty(); // 是否为空，等价于 size() == 0

  boolean contains(Object o); // 是否包含某个元素

  boolean containsAll(CustomCollection<?> c);

  Object[] toArray(); // 将集合转化为数组

  <T> T[] toArray(T[] a);

  boolean add(E e); // 添加元素

  boolean addAll(CustomCollection<? extends E> c);

  boolean remove(Object o); // 删除元素

  boolean removeAll(CustomCollection<?> c);

  boolean retainAll(CustomCollection<?> c); // 保留指定集合在当前集合存在的元素，就是取两个集合的交集

  void clear(); // 清空所有元素

  boolean equals(Object o);

  int hashCode();
}
