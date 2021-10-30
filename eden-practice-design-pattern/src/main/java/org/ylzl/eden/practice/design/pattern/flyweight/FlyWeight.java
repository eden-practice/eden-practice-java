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

package org.ylzl.eden.practice.design.pattern.flyweight;

/**
 * 享元模式
 *
 * @see Integer#valueOf(int)
 * @author gyl
 * @since 2.0.0
 */
public class FlyWeight {

  public static boolean cached(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high) {
      return IntegerCache.cache[i + (-IntegerCache.low)] == i;
    }
    return false;
  }

  private static class IntegerCache {
    static final int low = -128;
    static final int high = 256; // 超出 Integer 默认的 127
    static final Integer cache[] = new Integer[(high - low) + 1];

    static {
      int j = low;
      for (int k = 0; k < cache.length; k++) {
        cache[k] = new Integer(j++);
      }
    }
  }
}
