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

package org.ylzl.eden.practice.designpattern.prototype;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 浅拷贝测试
 *
 * @author gyl
 * @since 2.0.0
 */
class ShallowCopyTest {

	@Test
	void testClone() throws CloneNotSupportedException {
		ShallowCopy source = new ShallowCopy();
		source.setName("111");
		ShallowCopy.Inner inner = new ShallowCopy.Inner();
		inner.setValue("aaa");
		source.setInner(inner);

		ShallowCopy cloned = (ShallowCopy) source.clone();
		source.setName("222");

		System.out.println(source);
		System.out.println(cloned);
		assertEquals(source.getInner() == cloned.getInner(), true); // 深拷贝对象地址相同
	}
}
