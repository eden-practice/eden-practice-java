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

package org.ylzl.practice.algorithms.datastructures.bitmaps;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class Bitmap {

	public byte[] bitmapArray(int size){
		byte[] arr = new byte[(size >> 3) + 1];
		for (int i = 1; i <= size; i++) {
			add(arr, i);
		}
		return arr;
	}

	public void add(byte[] arr, int val) {
		int index = val >> 3; // num/8 得到 byte[] 的 index
		int position = val & 0x07; // num % 8 得到在 byte[index] 的位置
		arr[index] |= 1 << position;
	}

	public boolean contain(byte[] arr, int val) {
		int index = val >> 3;
		int position = val & 0x07;

		int a = arr[index] & (1 << position);
		return a != 0;
	}

	public void remove(byte[] arr, int val){
		int index = val >> 3;
		int position = val & 0x07;
		arr[index] &= ~(1 << position);
	}

  public static void main(String[] args) {
  	int size = 10_000_000;
		Runtime runtime = Runtime.getRuntime();
		long t1 = runtime.freeMemory();
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = i;
		}
		long t2 = runtime.freeMemory();
		System.out.println(String.format("普通数组共占用内存 %s M", (t1 - t2) / 1024.0 / 1024.0));

		long t3 = runtime.freeMemory();
		Bitmap bitmap = new Bitmap();
		bitmap.bitmapArray(size);
		long t4 = runtime.freeMemory();
		System.out.println(String.format("Bitmap 数组共占用内存 %s M", (t3 - t4) / 1024.0 / 1024.0));
  }
}
