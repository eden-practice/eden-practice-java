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

package org.ylzl.practice.algorithms.datastructures.trees;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class TrieTree {

  private TrieNode root = new TrieNode('/');

  public void insert(String inputs) {
    TrieNode p = root;
		char[] inputChars = inputs.toCharArray();
    for (int i = 0; i < inputChars.length; i++) {
      int index = inputChars[i] - 'a';
      if (p.childNodes[index] == null) {
        p.childNodes[index] = new TrieNode(inputChars[i]);
      }
      p = p.childNodes[index];
    }
    p.isEnd = true;
  }

  public boolean find(String inputs) {
		TrieNode p = root;
		char[] inputChars = inputs.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			int index = inputChars[i] - 'a';
			if (p.childNodes[index] == null) {
				return false;
			}
			p = p.childNodes[index];
		}
    return p.isEnd;
  }

  public static void main(String[] args) {
		TrieTree trieTree = new TrieTree();
		trieTree.insert("java");
		trieTree.insert("javascript");
		trieTree.insert("goland");
		trieTree.insert("python");
    System.out.println(trieTree.find("java"));
		System.out.println(trieTree.find("javaee"));
  }

  class TrieNode {
    TrieNode[] childNodes = new TrieNode[128];
    boolean isEnd;
    char data;

    public TrieNode(char data) {
      this.data = data;
    }
  }
}
