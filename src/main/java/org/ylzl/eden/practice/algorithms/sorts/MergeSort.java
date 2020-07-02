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
    int len = unsorted.length;
    mergeSort(unsorted, 0, unsorted.length - 1);
    print(unsorted);
  }

  public static void mergeSort(int[] unsorted, int left, int right) {
    int mid = (left + right) / 2; // 中间下标
    if (left < right) {
      mergeSort(unsorted, left, mid); // 递归拆分左边
      mergeSort(unsorted, mid + 1, right); // 递归拆分右边
      merge(unsorted, left, mid, right); // 合并左右
    }
  }

  public static void merge(int[] unsorted, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];
    int i = left;
    int j = mid + 1;
    int k = 0;
    while (i <= mid && j <= right) {
      if (unsorted[i] <= unsorted[j]) {
        temp[k++] = unsorted[i++];
      } else {
        temp[k++] = unsorted[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = unsorted[i++];
    }
    while (j <= right) {
      temp[k++] = unsorted[j++];
    }
    for (int m = 0; m < temp.length; m++) {
			unsorted[m + left] = temp[m];
    }
  }
}
