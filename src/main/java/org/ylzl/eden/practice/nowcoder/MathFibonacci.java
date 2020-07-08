package org.ylzl.eden.practice.nowcoder;

/**
 * 斐波那契数列（1、1、2、3、5、8、13、21、34、55、79、145......，n <= 39）
 *
 * @author gyl
 * @see <a
 *     href="https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">剑指
 *     Offer 原题</a>
 * @since 2.0.0
 */
public class MathFibonacci {

	private int[] cache = new int[40];

	{
		cache[0] = 1;
		cache[1] = 1;
		for (int i = 2; i < cache.length; i++) {
			cache[i] = cache[i - 2] + cache[i - 1];
		}
	}

	public int fibonacci(int n) {
		return cache[n];
	}
}
