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

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.ylzl.eden.practice.net.rpc.RpcRequest;
import org.ylzl.eden.practice.net.rpc.RpcResponse;
import org.ylzl.eden.practice.net.rpc.serializer.JsonSerializer;
import org.ylzl.eden.practice.net.rpc.serializer.Serializer;

/**
 * Rpc 服务端初始化
 *
 * @author gyl
 * @since 2.0.0
 */
public class RpcServerInitializer extends ChannelInitializer<SocketChannel> {

  @Autowired private RpcServerHandler handler;

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 4));
	  Serializer serializer = new JsonSerializer();
    pipeline.addLast(new RpcEncoder(RpcResponse.class,serializer));
    pipeline.addLast(new RpcDecoder(RpcRequest.class, serializer));
    pipeline.addLast(handler);
  }
}
