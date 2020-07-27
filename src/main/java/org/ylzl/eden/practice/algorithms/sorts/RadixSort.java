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

package org.ylzl.eden.practice.algorithms.sorts;

/**
 * 基数排序
 *
 * @author gyl
 * @since 2.0.0
 */
public class RadixSort extends AbstractSort {

	public static final int ASCII_RANGE = 128;

	public static void main(String[] args) {
		String[] unsorted = {"cbd", "gd", "sss", "abd", "jhr", "eww"};
		print(sort(unsorted));
	}

	private static String[] sort(String[] unsorted) {
		String[] sorted = new String[unsorted.length];
		for (int k = unsorted[0].length() - 1; k >= 0; k--) {
			int[] count = new int[ASCII_RANGE];
			for (int i = 0; i < unsorted.length; i++) {
				int index = rightToLeftCharIndex(unsorted[i], k);
				count[index]++;
			}

			StringBuilder s1 = new StringBuilder();
			for (int i = 1; i < count.length; i++) {
				count[i] = count[i] + count[i - 1];
				s1.append(count[i]).append(",");
			}
			System.out.println(s1.toString());

			for (int i = unsorted.length - 1; i >= 0; i--) {
				int index = rightToLeftCharIndex(unsorted[i], k); // 97
				int sortedIndex = count[index] - 1; //
				sorted[sortedIndex] = unsorted[i];
				count[index]--;
			}
			unsorted = sorted.clone();
		}
		return sorted;
	}

	private static int rightToLeftCharIndex(String s, int k) {
		if (s.length() < k + 1) {
			return 0;
		}
		return s.charAt(k);
	}

}
