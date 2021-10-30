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

package org.ylzl.practice.algorithms.sorts;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public abstract class AbstractSort {

	protected static void swap(int[] unsorted, int prev, int next) {
		int temp = unsorted[prev];
		unsorted[prev] = unsorted[next];
		unsorted[next] = temp;
	}

	protected static void print(int[] sorted) {
		StringBuilder sb = new StringBuilder();
		sb.append("排序后的结果：");
		for (int i = 0; i < sorted.length; i++) {
			sb.append(sorted[i]);
			if (i != sorted.length - 1) {
				sb.append(",");
			}
		}
    System.out.println(sb.toString());
	}

	protected static void print(String[] sorted) {
		StringBuilder sb = new StringBuilder();
		sb.append("排序后的结果：");
		for (int i = 0; i < sorted.length; i++) {
			sb.append(sorted[i]);
			if (i != sorted.length - 1) {
				sb.append(",");
			}
		}
		System.out.println(sb.toString());
	}

	protected static void print(double[] sorted) {
		StringBuilder sb = new StringBuilder();
		sb.append("排序后的结果：");
		for (int i = 0; i < sorted.length; i++) {
			sb.append(sorted[i]);
			if (i != sorted.length - 1) {
				sb.append(",");
			}
		}
		System.out.println(sb.toString());
	}
}
