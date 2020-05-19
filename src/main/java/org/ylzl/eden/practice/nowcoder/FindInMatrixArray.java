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

package org.ylzl.eden.practice.nowcoder;

/**
 * 二维数组中的查找
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @since 1.0.0
 */
public class FindInMatrixArray {

  public boolean find(int target, int[][] array) {
    if (array == null || array.length == 0 || array[0].length == 0) {
      return false;
    }
    int rows = array.length;
    int cols = array[0].length;
    int rowIndex = 0;
    int colIndex = cols - 1; // 从左上角开始遍历
    while (rowIndex <= rows - 1 && colIndex >= 0) {
      if (array[rowIndex][colIndex] == target) {
        return true;
      } else if (array[rowIndex][colIndex] < target) {
        rowIndex++;
      } else {
        colIndex--;
      }
    }
    return false;
  }
}
