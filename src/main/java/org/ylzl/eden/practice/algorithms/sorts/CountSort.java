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
 * 计数排序
 *
 * @author gyl
 * @since 2.0.0
 */
public class CountSort extends AbstractSort {

	public static void main(String[] args) {
		int[] unsorted = {22, 33, 66, 22, 11, 66, 55, 88, 0, 11, 66, 77, 99, 33, 44, 22, 77, 55, 11, 33, 44, 88, 0, 99, 11};
		sort(unsorted);
		print(unsorted);
	}

	private static void sort(int[] unsorted) {
		int max = unsorted[0];
		int min = unsorted[0];
		for (int i = 1; i < unsorted.length; i++) {
			if (unsorted[i] > max) {
				max = unsorted[i];
			}
			if (unsorted[i] < min) {
				min = unsorted[i];
			}
		}
		int size = max - min + 1;
		int[] count = new int[size];
		for (int i = 0; i < unsorted.length; i++) {
			count[Math.abs(unsorted[i] - size) - 1]++;
		}
		int index = 0;
		for (int j = 0; j < count.length; j++) {
			int temp = count[j];
			while (temp > 0) {
				unsorted[index++] = j;
				temp--;
			}
		}
	}
}
