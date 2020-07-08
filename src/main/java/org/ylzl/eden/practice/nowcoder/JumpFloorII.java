package org.ylzl.eden.practice.nowcoder;

/**
 * 变态跳台阶（归纳分析法/贪心算法）
 *
 * <br/> 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * @author gyl
 * @since 2.0.0
 * @see <a
 *     href="https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking">剑指
 *     Offer 原题</a>
 */
public class JumpFloorII {

	public int jump(int target) {
		// n=1 -> 1
		// n=2 -> 2
		// n=3 -> 4
		// n=4 -> 8
		// n-1 阶乘
		return target < 3 ? target : 1 << (target - 1);
	}
}
