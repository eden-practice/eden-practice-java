package org.ylzl.eden.practice.nowcoder;

import java.util.Stack;

/**
 * 使用两个栈实现队列
 *
 * @author gyl
 * @since 2.0.0
 * @see <a
 *     href="https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @see Stack#pop()
 */
public class QueueBuilderByTwoStack {

	private Stack<Integer> stack1 = new Stack<Integer>();

	private Stack<Integer> stack2 = new Stack<Integer>();

	public void push(int node) {
		stack1.push(node);
	}

	public int pop() {
		if (stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}

		if (stack2.isEmpty()) {
			throw new RuntimeException("stack2 is empty");
		}

		return stack2.pop();
	}
}
