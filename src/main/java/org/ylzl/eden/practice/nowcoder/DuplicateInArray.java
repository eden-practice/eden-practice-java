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

package org.ylzl.eden.practice.nowcoder;

/**
 * 查找数组中重复的任意一个数字
 *
 * @author gyl
 * @see <a href="https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指 Offer 原题</a>
 * @since 1.0.0
 */
public class DuplicateInArray {

	public boolean duplicate(int numbers[], int length, int[] duplication) {
		if (numbers == null || numbers.length == 0 || length <= 0) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			// eg：{2, 3, 1, 0, 2, 5}
			while (numbers[i] != i) {
				if (numbers[i] == numbers[numbers[i]]) { // 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内，不会越界
					duplication[0] = numbers[i];
					return true;
				}
				swap(numbers, i, numbers[i]);
			}
		}
		return false;
	}

	private void swap(int numbers[], int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
