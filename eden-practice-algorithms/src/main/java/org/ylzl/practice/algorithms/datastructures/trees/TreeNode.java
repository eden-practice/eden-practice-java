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

package org.ylzl.practice.algorithms.datastructures.trees;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class TreeNode {

	int val;

	TreeNode left;

	TreeNode right;

	public TreeNode(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "[" + val + "]";
	}

	public void print() {
		int maxLeftOffset = findMaxOffset(this, 0, true);
		int maxRightOffset = findMaxOffset(this, 0, false);
		int offset = Math.max(maxLeftOffset, maxRightOffset);
		Map<Integer, PrintLine> lineMap = new HashMap();
		calculateLines(this, offset, lineMap, 0, true);
		Iterator<Integer> lineNumbers = lineMap.keySet().iterator();
		int maxLine = 0;
		while (lineNumbers.hasNext()) {
			int lineNumber = lineNumbers.next();
			if (lineNumber > maxLine) {
				maxLine = lineNumber;
			}
		}
		for (int i = 0; i <= maxLine; i++) {
			PrintLine line = lineMap.get(i);
			if (line != null) {
				System.out.println(line.getLineString());
			}
		}
	}

	private void calculateLines(TreeNode parent, int offset, Map<Integer, PrintLine> lineMap, int level, boolean right) {
		if (parent == null) {
			return;
		}
		int nameOffset = parent.toString().length() / 2;
		PrintLine line = lineMap.get(level);
		if (line == null) {
			line = new PrintLine();
			lineMap.put(level, line);
		}
		line.putString(right ? offset : (offset - nameOffset), parent.toString());
		if (parent.left == null && parent.right == null) {
			return;
		}
		PrintLine separateLine = lineMap.get(level + 1);
		if (separateLine == null) {
			separateLine = new PrintLine();
			lineMap.put(level + 1, separateLine);
		}
		if (parent.left != null) {
			separateLine.putString(offset - 1, "/");
			calculateLines(parent.left, offset - nameOffset - 1, lineMap, level + 2, false);
		}
		if (parent.right != null) {
			separateLine.putString(offset + nameOffset + 1, "\\");
			calculateLines(parent.right, offset + nameOffset + 1, lineMap, level + 2, true);
		}
	}

	private int findMaxOffset(TreeNode parent, int offset, boolean findLeft) {
		if (parent != null) {
			offset += String.valueOf(parent.val).length();
		}
		if (findLeft && parent.left != null) {
			offset += 1;
			return findMaxOffset(parent.left, offset, true);
		}
		if (!findLeft && parent.right != null) {
			return findMaxOffset(parent.right, offset, false);
		}
		return offset;
	}

	private static class PrintLine {

		Map<Integer, String> printItemsMap = new HashMap<>();
		int maxOffset = 0;

		public void putString(int offset, String info) {
			printItemsMap.put(offset, info);
			if (offset > maxOffset) {
				maxOffset = offset;
			}
		}

		public String getLineString() {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i <= maxOffset; i++) {
				String info = printItemsMap.get(i);
				if (info == null) {
					buffer.append(" ");
				} else {
					buffer.append(info);
					i += info.length();
				}
			}
			return buffer.toString();
		}
	}

}
