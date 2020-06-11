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

package org.ylzl.eden.practice.nosql.redis.type;

import org.ylzl.eden.practice.nosql.redis.datastructures.Linkedlist;
import org.ylzl.eden.practice.nosql.redis.datastructures.ZipList;

/**
 * Redis 列表（链表/压缩列表）
 *
 * @author gyl
 * @since 2.0.0
 */
public class List {

	private Linkedlist linkedlist; // 当元素较多时使用

	private ZipList zipList; // 当元素较少时使用，分配连续内存块
}
