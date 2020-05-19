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

package org.ylzl.eden.practice.collections.list;

import java.io.Serializable;
import java.util.RandomAccess;

/**
 * 向量
 *
 * <p>Vector = ArrayList + synchronized </p>
 * <p>Vector 按 2 倍扩容，ArrayList 是 1.5 倍</p>
 * <p>已被 {@link java.util.Collections#synchronizedList(java.util.List)} 代替</p>
 *
 * @author gyl
 * @since 1.0.0
 */
@Deprecated
public class Vector<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, Serializable {

	@Override
	public E get(int index) {
		return null;
	}

	@Override
	public void clear() {

	}
}
