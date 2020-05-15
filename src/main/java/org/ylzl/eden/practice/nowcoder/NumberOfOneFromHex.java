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

package org.ylzl.eden.practice.nowcoder;

/**
 * 统计二进制 1 的个数
 *
 * @author gyl
 * @see <a href="https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指 Offer 原题</a>
 * @since 1.0.0
 */
public class NumberOfOneFromHex {

	public int numberOf(int n) {
		// return Integer.bitCount(n);
		// n       : 10110100
		// n-1     : 10110011 减去1，最后一个 1 之后全部反转
		// n&(n-1) : 10110000 与运算
		int cnt = 0;
		while (n != 0) {
			cnt++;
			n &= (n - 1);
		}
		return cnt;
	}
}
