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

package org.ylzl.practice.algorithms.datastructures.graphs;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * 十字矩阵
 *
 * @author gyl
 * @since 2.0.0
 */
public class Graph {

  private int size;

  private Vertex[] vertexes;

  private LinkedList<Integer> adj[];

  public Graph(int size) {
    this.size = size;
    // 初始化顶点和邻接矩阵
    vertexes = new Vertex[size];
    adj = new LinkedList[size];
    for (int i = 0; i < size; i++) {
      vertexes[i] = new Vertex(i);
      adj[i] = new LinkedList();
    }
  }

  private static class Vertex {

    int data;

    Vertex(int data) {
      this.data = data;
    }
  }

  public static void bfs(Graph graph) {
    LinkedList<Integer> queue = Lists.newLinkedList();
    boolean[] visited = new boolean[graph.size];
    queue.offer(0);
    while (!queue.isEmpty()) {
      int front = queue.poll();
      if (visited[front]) {
        continue;
      }

      System.out.println(graph.vertexes[front].data);
      visited[front] = true;
      for (int index : graph.adj[front]) {
        queue.offer(index);
      }
    }
  }

  public static void dfs(Graph graph, int start, boolean[] visited) {
    System.out.println(graph.vertexes[start].data);
    visited[start] = true;
    for (int index : graph.adj[start]) {
      if (!visited[index]) {
        dfs(graph, index, visited);
      }
    }


  }

  public static void main(String[] args) {
    Graph graph = new Graph(6);
    graph.adj[0].add(1);
    graph.adj[0].add(2);
    graph.adj[0].add(3);
    graph.adj[1].add(0);
    graph.adj[1].add(3);
    graph.adj[1].add(4);
    graph.adj[2].add(0);
    graph.adj[3].add(0);
    graph.adj[3].add(1);
    graph.adj[3].add(4);
    graph.adj[3].add(5);
    graph.adj[4].add(1);
    graph.adj[4].add(3);
    graph.adj[4].add(5);
    graph.adj[5].add(3);
    graph.adj[5].add(4);

    System.out.println("图的广度优先遍历：");
    bfs(graph);

    System.out.println("图的深度优先遍历：");
    dfs(graph, 0, new boolean[graph.size]);
  }
}
