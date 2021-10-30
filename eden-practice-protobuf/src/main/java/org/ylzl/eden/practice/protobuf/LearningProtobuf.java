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

package org.ylzl.eden.practice.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningProtobuf {

  public static void main(String[] args) throws InvalidProtocolBufferException {
		LearningProtos.User user = LearningProtos.User.newBuilder()
			.setLogin("gyl")
			.setEmail("1813986321@qq.com")
			.build();
    System.out.println(user);

    byte[] data = user.toByteArray();
		LearningProtos.User parseUser = LearningProtos.User.parseFrom(data);
    System.out.println(parseUser);
  }
}
