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
 * 桶排序
 *
 * @author gyl
 * @since 2.0.0
 */
public class BucketSort extends AbstractSort {

	public static void main(String[] args) {
		double[] unsorted = {22.01, 88.1, 0.5, 99, 11.11};
		sort(unsorted);
		print(unsorted);
	}

	private static void sort(double[] unsorted) {
		double max = unsorted[0];
		double min = unsorted[0];
		for (int i = 1; i < unsorted.length; i++) {
			if (unsorted[i] > max) {
				max = unsorted[i];
			}
			if (unsorted[i] < min) {
				min = unsorted[i];
			}
		}
		double parts = max - min;
	}
}
