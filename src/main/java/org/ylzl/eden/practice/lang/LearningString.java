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

import java.lang.reflect.Field;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningString {

	// 编译时已初始化，归属 heap 中的常量池
	private String a = "100";
	private String b = "200";
	private String c = "100200";
	private String d = "100" + "200";
	private String e = "100" + 200;
	// a 和 b 都是 new StringBuilder 来的，所以都不相等，都在 Heap
	private final String n = a + b;
	private String o = a + "200";
	private String p = a + b;
	private String q = a + b;

	private String nochange = "10086";

	public void equalsString() {
		System.out.println(c == d);
		System.out.println(c == e);
		System.out.println(c == n);
		System.out.println(c == o);
		System.out.println(c == p);
		System.out.println(d == e);
		System.out.println(p == q);
	}

	public void changeString() throws NoSuchFieldException, IllegalAccessException {
		System.out.println(nochange);
		System.out.println(nochange.hashCode());
		Field field = String.class.getDeclaredField("value");
		field.setAccessible(true);
		char[] v = (char[]) field.get(nochange);
		v[0] = '2';
    System.out.println(nochange);
		System.out.println(nochange.hashCode());

		Field hash = String.class.getDeclaredField("hash");
		hash.setAccessible(true);
		int h = (int) hash.get(nochange);
    System.out.println(h); // 被缓存了，没办法修改
		System.out.println(nochange.hashCode());
	}

  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		LearningString str = new LearningString();
		System.out.println("比较字符串：");
    str.equalsString();

    System.out.println("\n改变字符串内容：");
		str.changeString();

		System.out.println("\nintern()：");
		String internA = new String("AAA") + new String("BBB");
		internA.intern(); // 从常量池找不到 AAABBB，然后从堆也找不到 AAABBB，那么新建 AAABBB 到常量池，并返回引用
		String internB = "AAA" + "BBB"; // 取到了 intern 的 AAABBB
    System.out.println(internA == internB);

		String internD = "CCC" + "DDD";
		String internC = new String("CCC") + new String("DDD");
		internC.intern();
		System.out.println(internC == internD);
		System.out.println(internC.intern() == internD);

		String s1 = new String("1");
		String s2 = "1";
		System.out.println(s1.intern() == s2);
  }
}
