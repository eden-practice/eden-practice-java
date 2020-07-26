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

package org.ylzl.eden.practice.io.nio;

import java.nio.InvalidMarkException;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public abstract class Buffer {

	private int mark = -1;

	private int position = 0;

	private int limit;

	private int capacity;

	long address;

	public final int capacity() {
		return capacity;
	}

	public final int position() {
		return position;
	}

	public final int limit() {
		return limit;
	}

	public final Buffer mark() {
		mark = position;
		return this;
	}

	public final Buffer reset() {
		int m = mark;
    if (m < 0) {
      throw new InvalidMarkException();
    }
		position = m;
		return this;
	}

	public final Buffer clear() {
		position = 0;
		mark = -1;
		limit = capacity;
		return this;
	}

	public final Buffer flip() {
		position = 0;
		mark = -1;
		limit = position;
		return this;
	}

	public final Buffer rewind() {
		position = 0;
		mark = -1;
		return this;
	}

	public final int remaining() {
		return limit - position;
	}

	public final boolean hasRemaining() {
		return position < limit;
	}

	final void truncate() {
		mark = -1;
		position = 0;
		limit = 0;
		capacity = 0;
	}

	public abstract boolean isReadOnly();

	public abstract boolean hasArray();

	public abstract Object array();

	public abstract int arrayOffset();

	public abstract boolean isDirect();
}
