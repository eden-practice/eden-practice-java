package org.ylzl.eden.practice.net.rpc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ylzl.eden.practice.net.rpc.netty.client.NettyRpcClient;
import org.ylzl.eden.practice.net.rpc.netty.server.NettyRpcServer;
import org.ylzl.eden.practice.net.rpc.proxy.RpcClientProxy;
import org.ylzl.eden.practice.net.rpc.serializer.JsonSerializer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
class RpcTest {

  private static final String DEFAULT_HOST = "127.0.0.1";

  private static final int DEFAULT_PORT = 8089;

  private static final JsonSerializer jsonSerializer = new JsonSerializer();

  private RpcServer rpcServer;

  private RpcClient rpcClient;

  @BeforeEach
  void before() throws InterruptedException {
    rpcServer =
        new NettyRpcServer("netty server", DEFAULT_HOST, DEFAULT_PORT, jsonSerializer);
    rpcServer.startup();
    rpcClient = new NettyRpcClient("netty client", DEFAULT_HOST, DEFAULT_PORT, jsonSerializer);
		rpcClient.startup();
  }

  @Test
  void assertThatRemoteRpcSuccess() {
    RpcService rpcService = new RpcClientProxy(rpcClient).newProxyInstance(RpcService.class, 3000);
		assertDoesNotThrow(() -> {
			System.out.println(rpcService.invoke());
		});
  }

  @AfterEach
  void after() {
    rpcClient.shutdown();
  	rpcServer.shutdown();
  }
}
