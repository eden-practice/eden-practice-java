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
 * Redis 哈希表
 *
 * @author gyl
 * @since 2.0.0
 */
public class Hashtable {

	class Dict {

		private DictType type;

		// 一般只存 2 个哈希表，rehash 时使用，当 DictEntry 数组元素过大时，会影响插入性能，这个时候可以适当增加
		private DictHt[] rehashDictHt = new DictHt[2];

		private long rehashidx; // 保存 rehash 后指向哈希表数组的下标

		private int iterators;
	}

	class DictHt {

		private DictEntry[] dictEntries;

		private long size;

		private long sizemask;

		private long used;
	}

	class DictEntry {

		private String key;

		private Object value;

		private DictEntry next;
	}

	class DictType {

	}
}
