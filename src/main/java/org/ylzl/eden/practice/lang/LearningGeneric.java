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

package org.ylzl.eden.practice.lang;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningGeneric {

  public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		List list1 = new ArrayList<String>(); // IDE 类型擦除警告
		list1.add("hello");
		list1.add(123);
		list1.forEach(e -> System.out.println(e.getClass()));

		List<String> list2 = new ArrayList();
		list2.add("hello");
		list2.getClass().getMethod("add", Object.class).invoke(list2, 111); // 反射破坏
		list2.forEach(e -> System.out.println(e));

		// list2.add(123); // 编译器检查报错
  }
}
