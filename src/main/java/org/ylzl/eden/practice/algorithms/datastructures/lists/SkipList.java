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

package org.ylzl.eden.practice.algorithms.datastructures.lists;

import java.util.Random;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class SkipList {

	private static final int MAX_LEVEL = 16;

	private int levelCount = 1;

	private Random r = new Random();

	private SkipListNode head = new SkipListNode(MAX_LEVEL);

	class SkipListNode {

		private int data = -1;

		private SkipListNode forwards[];

		private int maxLevel = 32;

		public SkipListNode(int level) {
			forwards = new SkipListNode[level];
		}
	}

	// 1 ----------------> 7 -----------------> 17
	// 1 ------> 4 ------> 7 ------> 9 -------> 17
	// 1 -> 3 -> 4 -> 5 -> 7 -> 8 -> 9 -> 13 -> 17
	public SkipListNode find(int value) {
		SkipListNode p = head;
		for (int i = levelCount - 1; i >= 0; --i) { // 从最后一层开始查找
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				// 找到前一节点
				p = p.forwards[i];
			}
		}

		if (p.forwards[0] != null && p.forwards[0].data == value) {
			return p.forwards[0];
		}
		return null;
	}

	public void insert(int value) {
		int level = head.forwards[0] == null ? 1 : randomLevel();
		// 每次只增加一层，如果条件满足
		if (level > levelCount) {
			level = ++levelCount;
		}
		SkipListNode newNode = new SkipListNode(level);
		newNode.data = value;
		SkipListNode update[] = new SkipListNode[level];
		for (int i = 0; i < level; ++i) {
			update[i] = head;
		}

		SkipListNode p = head;
		// 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
		for (int i = levelCount - 1; i >= 0; --i) {
			while (p.forwards[i] != null && p.forwards[i].data < value) {
				// 找到前一节点
				p = p.forwards[i];
			}
			// levelCount 会 > level，所以加上判断
			if (level > i) {
				update[i] = p;
			}

		}
		for (int i = 0; i < level; ++i) {
			newNode.forwards[i] = update[i].forwards[i];
			update[i].forwards[i] = newNode;
		}
	}

	private int randomLevel() {
		int level = 1;
		for (int i = 1; i < MAX_LEVEL; ++i) {
			if (r.nextInt() % 2 == 1) {
				level++;
			}
		}
		return level;
	}

}
