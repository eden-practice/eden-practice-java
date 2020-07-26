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

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author gyl
 * @since 2.0.0
 */
public class QuickSort extends AbstractSort {

  public static void main(String[] args) {
    int[] arr = {5, 6, 2, 7, 9, 1, 3, 4, 8, 0};
    sort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }

	private static void sort(int[] unsorted, int start, int end) {
  	if (start >= end) {
  		return;
		}
  	int index = partition(unsorted, start, end);
  	sort(unsorted, start, index-1);
  	sort(unsorted, index+1, end);
	}

	private static int partition(int[] unsorted, int start, int end) {
  	int pivot = unsorted[start];
  	int left = start;
  	int right = end;
  	while (left != right) {
  		while (left < right && unsorted[right] > pivot) {
  			right--;
			}
  		while (left < right && unsorted[left] <= pivot) {
  			left++;
			}
  		if (left < right) {
  			swap(unsorted, left, right);
			}
		}
  	swap(unsorted, start, left);
  	return left;
	}
}
