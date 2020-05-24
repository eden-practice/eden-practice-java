/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
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

package org.ylzl.eden.practice.sorts;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author gyl
 * @see <a *
 *     href="https://mengxiangge.work/2019/01/06/algorithmic/#%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F">快速排序</a>
 * @since 2.0.0
 */
public class QuickSort {

  public static void main(String[] args) {
    int[] arr = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
    sort(arr, 0, arr.length - 1);
    System.out.println("结果：" + Arrays.toString(arr));
  }

  /**
   * 排序
   *
   * @param arr 待排序数组
   * @param left 数组最小下标
   * @param right 数组最大下标
   */
  public static void sort(int[] arr, int left, int right) {
    if (left < right) {
      int temp = partition(arr, left, right);
      System.out.println("分区结果：" + temp);
      sort(arr, left, temp - 1);
      sort(arr, temp + 1, right);
    }
  }

  /**
   * 分区
   *
   * @param arr 待排序数组
   * @param left 数组最小下标
   * @param right 数组最大下标
   * @return 基准数的下标
   */
  public static int partition(int[] arr, int left, int right) {
    int pivot = arr[left]; // 定义基准数，默认取数组的第一个元素 72
    while (left < right) { // 使用双向指针
      // 右向指针
      // 因为默认的基准数是在最左边，所以首先从右边开始遍历判断
      while (left < right && arr[right] > pivot) {
        right--; // 如果当前 arr[right] 比基准数大，则直接将右指针左移一位
        System.out.println("右向指针循环：" + Arrays.toString(arr));
      }
      System.out.println("右向指针跳出循环：" + arr[right] + "<=" + pivot);
      if (left < right) {
        arr[left++] = arr[right];
      }
      System.out.println("右向指针交换结果：" + Arrays.toString(arr));
      // 左向指针
      while (left < right && arr[left] <= pivot) {
        left++;
        System.out.println("左向指针循环：" + Arrays.toString(arr));
      }
      System.out.println("左向指针跳出循环：" + arr[left] + ">" + pivot);
      // 跳出上一个循环说明当前的arr[left]的值大于基准数，需要将该值填充到右边空出的位置，然后当前位置空出。
      if (left < right) {
        arr[right--] = arr[left];
      }
      System.out.println("左向指针交换结果：" + Arrays.toString(arr));
    }
    // 当循环结束说明左指针和右指针已经相遇，这时将基准数填入该位置，并返回该位置的下标
    arr[left] = pivot;
    return left;
  }
}
