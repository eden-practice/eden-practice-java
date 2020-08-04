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

import org.ylzl.eden.practice.nosql.redis.datastructures.Hashtable;
import org.ylzl.eden.practice.nosql.redis.datastructures.IntSet;

/**
 * Redis 集合（字节数组/哈希表）
 *
 * @author gyl
 * @since 2.0.0
 */
public class Set {

	private IntSet intSet; // 内部使用一个字节数组存放 Set 的元素，当 Set 需要包含整型数据时使用

	private Hashtable hashtable; // 使获取元素的时间复杂度降低为 O(1)
}
