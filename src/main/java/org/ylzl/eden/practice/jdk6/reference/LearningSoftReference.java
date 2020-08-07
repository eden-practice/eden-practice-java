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

package org.ylzl.eden.practice.jdk6.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningSoftReference {

	// -Xmx10m
	public static void main(String[] args) {
		SoftReference<byte[]> reference = new SoftReference<byte[]>(new byte[1024*1024*5]); // 5m
		System.out.println(reference.get());
		System.gc();
		System.out.println(reference.get());
		byte[] bytes = new byte[1024 * 1024 * 5]; // +5m，达到 -Xmx10m 上限
		System.out.println(reference.get());
	}
}
