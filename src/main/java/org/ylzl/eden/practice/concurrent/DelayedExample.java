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

package org.ylzl.eden.practice.concurrent;

import lombok.ToString;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟接口例子
 *
 * @author gyl
 * @since 2.0.0
 */
@ToString
public class DelayedExample implements java.util.concurrent.Delayed {

	private final String orderId; // 订单ID

	private final long timestamp; //  创建时间戳

	private final long expire; // 过期时间戳

	public DelayedExample(String orderId, long expireSeconds) {
		this.orderId = orderId;
		this.timestamp = System.currentTimeMillis();
		this.expire = this.timestamp + expireSeconds * 1000L;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return this.expire - System.currentTimeMillis();
	}

	@Override
	public int compareTo(Delayed o) {
		return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
	}
}
