package dd.code.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author zhangyong
 * @Description
 * @Date 7:54 下午 2021/11/9 2021
 **/
@Slf4j
/**
 * 股票买卖
 **/
public class SellStockTest {


    @Test
    public void climbTest(){
        int[] prices ={1,3,12,6,2,7};
        int i = maxProfit7(prices);
        System.out.println("SellStockTest.climbTest"+i);

    }


    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     **/
    public int maxProfit(int[] prices) {
        int max = 0;    //题目要求没有收益时返回0
        int m = prices.length;
        for (int i = 0; i < m - 1; i++)
            if (prices[i] < prices[i + 1])
                max += prices[i + 1] - prices[i];
        return max;
    }

    /**
     * 方法二：优雅简洁。 只能买卖一次：
     * 思路就是记录当前位置前面所有元素的最小值即可，多么简单啊，可惜刚开始我就是没想到！
     *
     * 时间复杂度：O(n)
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int res = 0;

        int min = prices[0];
        for (int i = 1; i < prices.length; i++) { // 以 min 买入，在 i 卖出
            if (prices[i] - min > res) {
                res = prices[i] - min;
            }

            if (prices[i] < min) {
                min = prices[i];
            }
        }
        return res;
    }






    /**只能交易一次，求最大利润。
     * 时间复杂度O(n)，空间复杂度O(1)。
     * @Author zhangyong
     * @Description
     * @Date 13:59 2022/1/24
     * @Param
     * @return
     **/
    public int maxProfit3(int[] prices) {
        int T_i10 = 0, T_i11 = Integer.MIN_VALUE;

        for (int price : prices) {
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, -price);
        }

        return T_i10;
    }

    /**不限交易次数，求最大利润。
     * 时间复杂度O(n)，空间复杂度O(1)。
     *初始条件：
     * T[-1][k][0] = 0, T[-1][k][1] = -Infinity
     * T[i][0][0] = 0, T[i][0][1] = -Infinity
     * 当-1天0持股时，利润是0。当-1天1持股时，不可能，定义为负无穷。
     * 当i天0交易0持股时，利润是0。当i天0交易1持股时，不可能，定义为负无穷。
     *
     * 递归公式：
     * T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])
     * T[i][k][1] = max(T[i-1][k][1], T[i-1][k-1][0] - prices[i])
     *
     * 当i天要变成0持股（清仓）时，可以休停保持i-1天持股，也可以卖出获得今日股价price。取较大值。
     * 当i天要变成1持股（买进）时，可以休停保持i-1天持股，也可以i-1卖出后i买进减去今日股价price。取较大值。
     **/
    public int maxProfit4(int[] prices) {
        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int T_ik0_old = T_ik0;//上一次清仓后的收益
            T_ik0 = Math.max(T_ik0, T_ik1 + price);//上次买入并当次清仓后的收益
            T_ik1 = Math.max(T_ik1, T_ik0_old - price);//上一次清仓后的并在当次买入后的余额
        }

        return T_ik0;
    }

    /**交易次数为2，求最大利润。
     * 时间复杂度O(n)，空间复杂度O(1)。
     * @Author zhangyong
     * @Description
     * @Date 14:05 2022/1/24
     * @Param [prices]
     * @return int
     **/
    public int maxProfit5(int[] prices) {
        int T_i10 = 0, T_i11 = Integer.MIN_VALUE,
                T_i20 = 0, T_i21 = Integer.MIN_VALUE;

        for (int price : prices) {
            T_i20 = Math.max(T_i20, T_i21 + price);
            T_i21 = Math.max(T_i21, T_i10 - price);
            T_i10 = Math.max(T_i10, T_i11 + price);
            T_i11 = Math.max(T_i11, -price);
        }

        return T_i20;
    }

    /**交易次数k为不定值，求最大利润。
     * 时间复杂度O(kn)，空间复杂度O(k)。
     * @Author zhangyong
     * @Description 
     * @Date 14:06 2022/1/24
     * @Param [k, prices]
     * @return int
     **/
    public int maxProfit6(int k, int[] prices) {
        if (k >= prices.length >>> 1) {
            int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

            for (int price : prices) {
                int T_ik0_old = T_ik0;
                T_ik0 = Math.max(T_ik0, T_ik1 + price);
                T_ik1 = Math.max(T_ik1, T_ik0_old - price);
            }

            return T_ik0;
        }

        int[] T_ik0 = new int[k + 1];
        int[] T_ik1 = new int[k + 1];
        Arrays.fill(T_ik1, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int j = k; j > 0; j--) {
                T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);
                T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
            }
        }

        return T_ik0[k];
    }
    /**交易次数k不限，但不能连续2天交易（卖完隔一天才能买），求最大利润。
     * 时间复杂度O(n)，空间复杂度O(1)。
     * @Author zhangyong
     * @Description 
     * @Date 14:08 2022/1/24
     * @Param [prices]
     * @return int
     **/
    public int maxProfit7(int[] prices) {
        int T_ik0_pre = 0, T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_pre - price);
            T_ik0_pre = T_ik0_old;
        }

        return T_ik0;
    }

    /**交易次数k不限，但每次交易有交易费fee，求最大利润。
     * 时间复杂度O(n)，空间复杂度O(1)。
     * @Author zhangyong
     * @Description 
     * @Date 14:08 2022/1/24
     * @Param [prices, fee]
     * @return int
     **/
    public int maxProfit8(int[] prices, int fee) {
        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_old - price - fee);
        }

        return T_ik0;
    }

    /**解法2：卖出时收费（long替换int防止溢出错误）
     * @Author zhangyong
     * @Description
     * @Date 14:09 2022/1/24
     * @Param [prices, fee]
     * @return int
     **/
    public int maxProfit82(int[] prices, int fee) {
        long T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            long T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price - fee);
            T_ik1 = Math.max(T_ik1, T_ik0_old - price);
        }

        return (int)T_ik0;
    }

}
