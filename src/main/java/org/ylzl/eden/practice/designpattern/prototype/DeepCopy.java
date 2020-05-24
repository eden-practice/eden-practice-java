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

import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;

/**
 * 深拷贝
 *
 * @author gyl
 * @since 2.0.0
 */
@Data
public class DeepCopy implements Serializable, Cloneable {

  private static final long serialVersionUID = -2771561749348262399L;

  private String name;

  private Inner inner;

  @Data
  public static class Inner implements Serializable {

	  private static final long serialVersionUID = 7663867707873720997L;

	  private String value;
  }

  @SneakyThrows
  @Override
  protected Object clone() throws CloneNotSupportedException {
    try (ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteArrayOut); ) {
      objectOut.writeObject(this);
      try (ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(byteArrayOut.toByteArray());
          ObjectInputStream objectIn = new ObjectInputStream(byteArrayIn); ) {
        return (Serializable) objectIn.readObject();
      }
    }
  }
}
