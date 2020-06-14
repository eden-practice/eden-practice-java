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

import lombok.Data;

/**
 * Redis 跳跃表
 *
 * @author gyl
 * @since 2.0.0
 */
@Data
public class SkipList {

	private SkipListNode head; // 头部节点

	private SkipListNode tail; // 尾部节点

	private Long length;  // 元素的个数

	private int level; // 最大层级

	@Data
  static class SkipListNode {

    private SDS ele; // 数据域

    private double score; // 分值

    private SkipListNode backward; // 前一个节点

    private SkipListLevel[] levels; // 最大值为 32，由 ZSKIPLIST_MAXLEVEL 定义

		@Data
		static class SkipListLevel {

      private SkipListNode forward; // 下一个节点

      private long span; // 距离下一个节点的跨度
    }
  }
}
