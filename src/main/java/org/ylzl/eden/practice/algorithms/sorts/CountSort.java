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
    int[] unsorted = {
      22, 33, 66, 22, 11, 66, 55, 88, 10, 11,
			66, 77, 99, 33, 44, 22, 77, 55, 11, 33
    };
    sort(unsorted);
    print(unsorted);
  }

  private static void sort(int[] unsorted) {
    int len = unsorted.length;
    int max = unsorted[0];
    int min = unsorted[0];
    for (int i = 1; i < len; i++) {
      if (max < unsorted[i]) {
        max = unsorted[i];
      }
      if (min > unsorted[i]) {
        min = unsorted[i];
      }
    }

    int size = max - min + 1;
    int[] counts = new int[size];
    for (int j = 0; j < len; j++) {
      counts[unsorted[j] - min]++;
    }

    int index = 0;
    for (int k = 0; k < size; k++) {
      int count = counts[k];
      while (count > 0) {
        unsorted[index++] = k+min;
        count--;
      }
    }
  }
}
