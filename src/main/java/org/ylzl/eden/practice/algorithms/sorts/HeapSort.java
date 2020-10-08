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
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class HeapSort {

  public static void main(String[] args) {
/*    int[] arr = {5, 6, 2, 7, 9, 1, 3, 4, 8, 0};*/

    int[] array = new int[] {1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
    upAdjust(array);
    System.out.println(Arrays.toString(array));

    array = new int[] {7, 1, 3, 10, 5, 2, 8, 9, 6};
    buildHeap(array);
    System.out.println(Arrays.toString(array));
  }

  public static void buildHeap(int[] array) {
    // 从最后一个非叶子节点开始，依次下沉调整
    for (int i = array.length / 2; i >= 0; i--) {
      downAdjust(array, i, array.length - 1);
    }
  }

  public static void upAdjust(int[] array) {
    int childIndex = array.length - 1;
    int parentIndex = (childIndex - 1) / 2;
    int temp = array[childIndex];
    while (childIndex > 0 && temp < array[parentIndex]) {
      array[childIndex] = array[parentIndex];
      childIndex = parentIndex;
      parentIndex = (parentIndex - 1) / 2;
    }
    array[childIndex] = temp;
  }

  public static void downAdjust(int[] array, int parentIndex, int length) {
    int temp = array[parentIndex];
    int childIndex = 2 * parentIndex + 1;
    while (childIndex < length) {
      // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
      if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
        childIndex++;
      }
      // 如果父节点小于任何一个孩子的值，直接跳出
      if (temp <= array[childIndex]) break;
      // 无需真正交换，单向赋值即可
      array[parentIndex] = array[childIndex];
      parentIndex = childIndex;
      childIndex = 2 * childIndex + 1;
    }
    array[parentIndex] = temp;
  }
}
