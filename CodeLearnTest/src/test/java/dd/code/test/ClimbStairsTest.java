package dd.code.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author zhangyong
 * @Description
 * @Date 7:54 下午 2021/11/9 2021
 **/
@Slf4j
/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 **/
public class ClimbStairsTest {


    @Test
    public void climbTest(){
        int sum = climbStairs3(5);
        System.out.println(sum);

    }


    /**一：动态规划
     * 到达第 i 阶的方法总数 = 第 i -1 阶方法数 + 第 i -2 阶方法数
     *
     * 时间复杂度： O(n) 单循环到 n
     * 空间复杂度：O(n) dp 数组用了 n 空间
     */
    public int climbStairs3(int n) {
        if ( n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i -2];
        }
        return dp[n];
    }

    /**
     * 解法二： 斐波那契
     *
     * 时间复杂度： O(n) 单循环到 n
     * 空间复杂度：O(1)
     **/
    public int climbStairs(int n) {
        if ( n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
