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
 * 桶排序
 *
 * @author gyl
 * @since 2.0.0
 */
public class BucketSort extends AbstractSort {

  public static void main(String[] args) {
    double[] unsorted = {22.01, 88.1, 0.5, 99, 11.11, 66.66, 12.34, 233, 11};
    sort(unsorted, 5);
    print(unsorted);
  }

  private static void sort(double[] unsorted, int bucketSize) {
    int len = unsorted.length;
    double max = unsorted[0];
    double min = unsorted[0];
    for (int i = 1; i < len; i++) {
      if (unsorted[i] > max) {
        max = unsorted[i];
      } else if (unsorted[i] < min) {
        min = unsorted[i];
      }
    }

		int bucketCount = (int) Math.floor((max - min) / bucketSize) + 1;
		double[][] buckets = new double[bucketCount][0];
		for (int i = 0; i < len; i++) {
			int index = (int) Math.floor((unsorted[i] - min) / bucketSize); // 计算出目标桶的位置
			buckets[index] = grow(buckets[index], unsorted[i]); // 对应桶增加一个元素
		}

		int index = 0;
    for (int j = 0; j < bucketCount; j++) { // k
      if (buckets[j].length <= 0) {
        continue;
      }
      insertSort(buckets[j]);
      for (double value : buckets[j]) {
        unsorted[index++] = value;
      }
		}
  }

	private static double[] grow(double[] arr, double value) {
		arr = Arrays.copyOf(arr, arr.length + 1);
		arr[arr.length - 1] = value;
		return arr;
	}

	private static void insertSort(double[] arr) {
  	for (int i = 0; i < arr.length; i++) {
  		int insertIndex = i;
			double insertValue = arr[i];
  		while (insertIndex > 0 && insertValue < arr[insertIndex-1]) {
  			arr[insertIndex] = arr[insertIndex-1];
				insertIndex--;
  		}
  		if (insertIndex != i) {
				arr[insertIndex] = insertValue;
			}
		}
	}
}
