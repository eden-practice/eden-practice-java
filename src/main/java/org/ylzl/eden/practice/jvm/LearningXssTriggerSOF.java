/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
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

package org.ylzl.eden.practice.jvm;

/**
 * 验证 Xss 参数对栈的深度的影响
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningXssTriggerSOF {

  private static int dept = 0;

  public static void recursion() {
    // 减少局部变量的声明也可以节省栈帧大小，增加调用深度
    long a = 1;
    dept++;
    recursion();
  }

  public static void main(String args[]) {
    try {
      recursion();
    } catch (Throwable e) {
      // 设置 -Xss128k，输出 363，只有一个局部变量时，栈的深度提升到 1822
      // 设置 -Xss1024k，输出 24486~29980
      System.out.println("调用栈的深度为：" + dept);
      e.printStackTrace();
    }
  }
}
