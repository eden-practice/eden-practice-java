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
 * 归并排序
 *
 * @author gyl
 * @since 2.0.0
 */
public class MergeSort extends AbstractSort {

  public static void main(String[] args) {
    int[] unsorted = {9, 6, 2, 7, 5, 1, 3, 4, 8, 0};
    sort(unsorted, 0, unsorted.length - 1);
    print(unsorted);
  }

	private static void sort(int[] unsorted, int start, int end) {
		if (start >= end) {
			return;
		}
		int mid = start + ((end - start) >> 1);
		sort(unsorted, start, mid);
		sort(unsorted, mid+1, end);
		merge(unsorted, start, mid, end);
	}

	// 9, 6, 2, 7, 5, 1, 3, 4, 8, 0
	// 6, 9, 2, 7
	private static void merge(int[] unsorted, int start, int mid, int end) {
  	int[] merge = new int[end - start + 1];
  	int mergeIndex = 0;
  	int left = start;
  	int right = mid + 1;
  	while (left <= mid && right <= end) {
  		if (unsorted[left] <= unsorted[right]) {
				merge[mergeIndex++] = unsorted[left++];
			} else {
				merge[mergeIndex++] = unsorted[right++];
			}
		}
		while (left <= mid) {
			merge[mergeIndex++] = unsorted[left++];
		}
		while (right <= end) {
			merge[mergeIndex++] = unsorted[right++];
		}
		for (int i = 0; i < merge.length; i++) {
			unsorted[i + start] = merge[i];
		}
	}

/*	private static void sort(int[] unsorted, int start, int end) {
  	if (start >= end) {
  		return;
		}
  	int mid = start + ((end - start) >> 1);
  	sort(unsorted, start, mid);
  	sort(unsorted, mid+1, end);
  	merge(unsorted, start, mid, end);
	}

	private static void merge(int[] unsorted, int start, int mid, int end) {
  	int[] merge = new int[end - start + 1];
  	int mergeIndex = 0;
  	int left = start;
  	int right = mid + 1;
  	while (left <= mid && right <= end) {
  		if (unsorted[left] <= unsorted[right]) {
  			merge[mergeIndex++] = unsorted[left++];
			} else {
				merge[mergeIndex++] = unsorted[right++];
			}
		}
		while (left <= mid) {
			merge[mergeIndex++] = unsorted[left++];
		}
		while (right <= end) {
			merge[mergeIndex++] = unsorted[right++];
		}
		for (int m = 0; m < merge.length; m++) {
			unsorted[m + start] = merge[m];
		}
	}*/
}
