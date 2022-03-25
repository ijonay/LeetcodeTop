package dd.code.test;

import org.junit.Test;

/**最长回文子串
 * @Author zhangyong
 * @Description
 * @Date 20:46 2022/3/16 2022
 **/
public class longSubString {
    @Test
    public void test(){
        String aa ="sdfafdsfdfgdfgsdgfds";
        String s = longestPalindrome(aa);
        System.out.println(s);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);

            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len-1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    public static  int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return (R-1) - (L+1) + 1;
    }


    /**给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @Author zhangyong
     * @Description 
     * @Date 13:48 2022/3/23
     * @Param [nums]
     * @return int
     **/
    public int maxSubArray(int[] nums) {
        int current=nums[0];
        int sum=nums[0];
        //我们考虑如果全是负数，那么返回最大的负数，如果最后的和为正，那么就使用扫描法
        for(int i=1;i<nums.length;i++) {
            if(current<0)current=nums[i];//当前数小于0 肯定会舍去（否则将会影响接下来的和），换为下一个数
            else current+=nums[i];//如果当前数不小于0，那么他会对接下来的和有积极影响
            if(current>sum)sum=current;//这里既实现了负数返回最大也实现了扫描法
            //这里其实已经隐式的列举了所有可能，保留了所有可能的最大值
        }
        return sum;
    }

    //动态规划法
    public int maxSubArray2(int[] nums) {// 动态规划法
        int sum=nums[0];
        int n=nums[0];
        for(int i=1;i<nums.length;i++) {
            if(n>0)n+=nums[i];
            else n=nums[i];
            if(sum<n)sum=n;
        }
        return sum;
    }



}
