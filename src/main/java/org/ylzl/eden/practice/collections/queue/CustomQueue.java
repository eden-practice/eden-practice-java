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

/**
 * 队列接口
 *
 * @author gyl
 * @since 2.0.0
 */
public interface CustomQueue<E> extends CustomCollection<E> {

	boolean add(E e); // 从队列尾部插入一个元素，如果插入超出队列的容量，抛出异常

	boolean offer(E e); // 从队列尾部插入一个元素，如果插入超出队列的容量，不抛出异常

	E remove(); // 移除队列第一个元素，如果队列为空，抛出异常

	E poll(); // 拉取队列第一个元素，并从队列移除

	E element(); // 检索队列第一个元素，在队列保留，如果队列为空，抛出异常

	E peek(); // 检索队列第一个元素，在队列保留，如果队列为空，返回 Null
}
