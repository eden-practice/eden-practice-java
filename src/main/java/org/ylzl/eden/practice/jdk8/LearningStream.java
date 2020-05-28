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

package org.ylzl.eden.practice.jdk8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 学习 Java8 Stream
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningStream {

  public static void filterAndCount() {
    List<String> stringList = Arrays.asList("1", "", "2", "3", "2", "", "1");
    long count = stringList.stream().filter(String::isEmpty).count();
    System.out.println(stringList + " 过滤空字符串的统计数为：" + count);
    long unsafeCount = stringList.parallelStream().filter(String::isEmpty).count();
    System.out.println(stringList + " 并行过滤空字符串的统计数为：" + unsafeCount);
  }

  public static void collect() {
    List<String> stringList = Arrays.asList("1", "1", "2", "3", "2", "2", "1");
    Set<String> stringSet = stringList.stream().collect(Collectors.toSet());
    System.out.println(stringList + " 转化为集合: " + stringSet);
  }

  public static void map() {
    List<Integer> integerList = Arrays.asList(1, 1, 2, 3, 2, 2, 1);
    List<Integer> calList =
        integerList.stream().map(i -> i << 1).distinct().collect(Collectors.toList());
    System.out.println(integerList + " 转化并做平方运算: " + calList);
  }

  public static void summaryStatistics() {
    List<Integer> integerList = Arrays.asList(1, 4, 2, 3, 2, 3, 2);
    IntSummaryStatistics statistics = integerList.stream().mapToInt((x) -> x).summaryStatistics();
    System.out.println(integerList + " 列表中最大的数：" + statistics.getMax());
    System.out.println(integerList + " 列表中最小的数：" + statistics.getMin());
    System.out.println(integerList + " 所有数之和：" + statistics.getSum());
    System.out.println(integerList + " 平均数：" + statistics.getAverage());
  }

  public static void limitAndSkip() {
    List<Integer> integerList = Arrays.asList(1, 2, 4, 5, 6, 3, 7, 8);
    List<Integer> pageList =
        integerList.stream()
            .skip(4)
            .limit(2)
            .sorted()
            .collect(Collectors.toList()); // 要先调用 skip 再调用 limit
    System.out.println(integerList + " 按第 4 页取 2 行并排序得：" + pageList);
  }

  public static void main(String[] args) {
    LearningStream.filterAndCount();
    LearningStream.collect();
    LearningStream.map();
    LearningStream.limitAndSkip();
		LearningStream.summaryStatistics();
  }
}
