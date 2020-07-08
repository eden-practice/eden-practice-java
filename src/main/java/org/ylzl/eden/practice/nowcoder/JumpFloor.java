package org.ylzl.eden.practice.nowcoder;

/**
 * 跳台阶（递归/归纳分析法）
 *
 * <br/> 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 *
 * @author gyl
 * @since 2.0.0
 * @see <a
 *     href="https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking">剑指
 *     Offer 原题</a>
 */
public class JumpFloor {

	public int jump(int target) {
//		return target < 3? target : jump(target - 2) + jump(target - 1); 递归会重复计算哦哦哦
		if (target < 3) {
			return target;
		}
		int pre1 = 2;
		int pre2 = 1;
		int result = 0;
		for (int i = 2; i < target; i++) {
			result = pre1 + pre2;
			pre2 = pre1;
			pre1 = result;
		}
		return result;
	}
}
