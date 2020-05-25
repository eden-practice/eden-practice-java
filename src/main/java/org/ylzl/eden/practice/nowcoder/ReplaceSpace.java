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

package org.ylzl.eden.practice.nowcoder;

/**
 * 替换空格为 %20
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @see java.lang.StringBuffer#append(String)
 * @see java.lang.StringBuffer#charAt
 * @see java.lang.StringBuffer#setCharAt
 * @since 2.0.0
 */
public class ReplaceSpace {

  public String replaceSpace(StringBuffer str) {
    //		return str.toString().replaceAll(" ", "%20");
    int p1 = str.length() - 1;
    for (int i = 0; i <= p1; i++) {
      if (str.charAt(i) == ' ') {
        str.append("  "); // %20 占 3 个字符
      }
    }

    int p2 = str.length() - 1;
    while (p1 >= 0 && p2 > p1) {
      char c = str.charAt(p1--);
      if (c == ' ') {
        str.setCharAt(p2--, '0');
        str.setCharAt(p2--, '2');
        str.setCharAt(p2--, '%');
      } else {
        str.setCharAt(p2--, c);
      }
    }
    return str.toString();
  }
}
