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

package org.ylzl.practice.algorithms.searches;

/**
 * 二分查找
 *
 * @author gyl
 * @since 2.0.0
 */
public class BinarySearch {

	private static int binarySearch(int[] nums, int target) {
		int low = 0;
		int high = nums.length -1;
		while (low <= high) {
			int middle = low + ((high - low) >> 1);
			if (target == nums[middle]) {
				return middle;
			}
			if (target < nums[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

  public static void main(String[] args) {
		int[] sorted = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int find = 10;
    System.out.println(binarySearch(sorted, find));
  }
}
