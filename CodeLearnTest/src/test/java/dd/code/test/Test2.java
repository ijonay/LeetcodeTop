package dd.code.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.util.Length;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Function;

/**
 * @Author zhangyong
 * @Description
 * @Date 13:56 2022/1/26 2022
 **/
public class Test2 {
    @Test
    public void longSubStr(){
        String s ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
              String[] ss = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
        List<String> list = Arrays.asList(ss);
        boolean contain = wordBreak(s,list);
        System.out.println(contain);
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        if(s.isEmpty()){
            return false;
        }
        boolean isContains = false;
        int i = 0;
        return wordBreak2(s,wordDict, i);

    }

    public boolean wordBreak2(String s, List<String> wordDict, int i) {
         i++;
         if(i>10){
            return false;
         }
        if(s.isEmpty()){
            return true;
        }
        String prefix = s.substring(0,1);
        boolean isContains = false;
        for(String word : wordDict){
            if(word.startsWith(prefix) && s.startsWith(word)){
                String subStr = s.substring(word.length());
                isContains = true && wordBreak2(subStr, wordDict, i);
                if(isContains){
                    break;
                }
            }
        }
        return isContains;

    }
    public int findKthLargest(int[] nums, int k) {
        //    PriorityQueue<Integer> heap = new PriorityQueue<>();
        //     for (int num : nums) {
        //         heap.add(num);
        //         if (heap.size() > k) {
        //             heap.poll();
        //         }
        //     }
        //     return heap.peek();
        int n = nums.length;
        int dev = partition(nums, 0, n-1);
        while(true){
            if(dev == n-k){
                break;

            }else if(dev < n-k){
                dev = partition(nums, dev+1, n-1);
            }else{
                dev = partition(nums, 0, dev -1);
            }
        }
        return nums[dev];

    }

    public int partition(int[] nums, int left, int right){
        int mid = left + new Random().nextInt(right - left+1);
        int index = nums[mid];
        while(left< right){

            while(left <= right && nums[right] > index ){
                right--;
            }

            while(left <= right && nums[left] < index ){
                left++;
            }
            swap(nums, left,right);
        }
//        swap(nums, left,mid);
        return left;
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public void getLongSubStr(String s){
        int length = s.length();
        int maxSize =0;
        for(int i=0; i<length; i++){
            int maxSubSize = doGetStr(s,i,i,length);
            int maxSubSize2 = doGetStr(s,i,i+1,length);
            if(maxSubSize>maxSize){
                maxSize = maxSubSize;
            }
            if(maxSubSize2>maxSize){
                maxSize = maxSubSize2;
            }
        }
        System.out.printf("maxSize"+maxSize);

    }

    public int doGetStr(String s,int L,int R, int length){
        while(L>=0 && R< length && s.charAt(L) == s.charAt(R) ){
            L--;
            R++;

        }
        return R-L+1;
    }


}