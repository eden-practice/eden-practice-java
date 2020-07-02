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
 * 选择排序
 *
 * @author gyl
 * @since 2.0.0
 */
public class SelectSort extends AbstractSort {

  public static void main(String[] args) {
    int[] unsorted = {9, 6, 2, 7, 5, 1, 3, 4, 8, 0};
    int len = unsorted.length;

    int swapCount = 0;
    for (int i = 0; i < len - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < len; j++) {
      	if (unsorted[j] < unsorted[minIndex]) {
      		minIndex = j;
				}
			}
      if (minIndex != i) {
      	swap(unsorted, i, minIndex);
				swapCount++;
			}
    }
    print(unsorted);
		System.out.println("交换次数：" + swapCount);
  }
}
