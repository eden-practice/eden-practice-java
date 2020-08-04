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

package org.ylzl.eden.practice.nosql.redis.datastructures;

/**
 * Redis 简单动态字符串
 *
 * @author gyl
 * @since 2.0.0
 */
public class SDS {

	/**
	 * buf 中已占用空间的长度
	 */
	private int len; // SDS 遵循 C 字符串以空字符 `\n`结尾的惯例，但是那 1 个字节不计算在 len 中。

	/**
	 * 数据空间, Redis 可以保存字符也可以保存二进制
	 */
	private char[] buf;

	/**
	 * buf 中剩余可用空间的长度，用于减少内存分配次数
	 * Redis 新版本好像去除了
	 */
	private int free; // Redis 的 SDS 中内置了一个 sdscat 函数，用于字符串的拼接，如果 free 的值不够，会再申请内存空间，避免溢出。
}
