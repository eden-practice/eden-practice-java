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

package org.ylzl.eden.practice.leetcode;

/**
 * 爬楼梯，给定一个 n 阶，每次只能爬 1 - 2 阶，统计有几种方法爬到 n 阶。
 *
 * 使用斐波那契数列实现
 *
 * @author gyl
 * @see <a href="https://leetcode-cn.com/problems/climbing-stairs/submissions">leetcode 原题</a>
 * @since 1.0.0
 */
public class ClimbStairs {

	public int climbStairs(int n) {
		if (n <= 2) {
			return n;
		}

		int fisrt = 2; // 差 2 阶
		int second = 1; // 差 1 阶
		for (int i=2; i <n; i++) {
			int sum = fisrt + second; // p(n) = p(n-1) + p(n-2);
			second = fisrt;
			fisrt = sum;
		}
		return fisrt;
	}
}
