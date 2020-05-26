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

package org.ylzl.eden.practice.net.rpc.netty;

import java.nio.channels.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通道容器
 *
 * @author gyl
 * @since 2.0.0
 */
public class ChannelHolder {

  private Map<String, Channel> channels = new ConcurrentHashMap<>();

  public ChannelHolder put(String key, Channel channel) {
    channels.put(key, channel);
    return this;
  }

  public Channel get(String key) {
    return channels.get(key);
  }

  public void remove(String key) {
    this.channels.remove(key);
  }

  public int size() {
    return channels.size();
  }
}
