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

package org.ylzl.eden.practice.caching;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LRU 缓存淘汰算法
 *
 * @author gyl
 * @since 2.0.0
 */
public class LRUCache<K, V> implements Cache<K, V> {

  private final int maxCapacity;

  @Getter
  private final Map<K, Node> cache;

  private int size;

  private Node head;

  private Node tail;

  private final Lock lock = new ReentrantLock();

  public LRUCache(int maxCapacity) {
    this.maxCapacity = maxCapacity;
    cache = Maps.newHashMap();
  }

	@ToString
  class Node {
    K key;
    V value;
    Node pre;
    Node next;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  @Override
  public boolean containsKey(Object key) {
    return false;
  }

  @Override
  public V get(Object key) {
    Node node = cache.get(key);
    if (node == null) {
      return null;
    }
    // 1 -> 2 -> 3, node = 2
    Node left = node.pre; // pre = (node.pre = 1)
		Node right = node.next;
		if (left != null) {
			left.next = right; // 1 -> 3
    }
		if (right != null) {
			right.pre = left;
		}

    if (tail != null) {
    	tail.next = node;
			node.pre = tail;
		}
    return node.value;
  }

  @Override
  public void put(K key, V value) {
		Node newNode = new Node(key, value);
		cache.put(key, newNode);
		if (head == null) {
			head = newNode;
			tail = newNode;
			size++;
			return;
		}

		if (size() > maxCapacity) {
			newNode.pre = tail;
			head = head.next;
		} else {
			if (size() != 1) {
				tail.next = newNode;
			}
			size++;
		}
  }

  @Override
  public int size() {
    return size;
  }

  public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(3);
		lruCache.put(1, "A");
		lruCache.put(2, "B");
		lruCache.put(3, "C");
		lruCache.put(4, "D");
    System.out.println(lruCache.getCache());
  }
}
