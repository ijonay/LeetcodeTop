package dd.code.test;

import org.junit.Test;

import java.util.*;

/**
 * @Author zhangyong
 * @Description
 * @Date 16:01 2022/3/25 2022
 **/
public class mergeKListsTest {



    @Test
    public void test(){
        int[] nums =new int[]{0,3,7,2,5,8,4,6,0,1};
        int i = longestConsecutive(nums);


    }
    public int longestConsecutive(int[] nums) {
        int maxNum =0;
        for(int i =0; i< nums.length;i++){
            if(nums[i]>maxNum){
                maxNum = nums[i];
            }
        }
        int[] hashNums = new int[maxNum+1];
        for(int i =0; i< nums.length;i++){
            hashNums[nums[i]] = 1;
        }
        int maxLength =0;
        int index =-1;
        for(int i =0; i< hashNums.length;i++){

            if(hashNums[i]>0 ){
                maxLength = Math.max(maxLength, i-index);

            }else{
                index = i;
            }
        }
        return maxLength;

    }

    /**
     * K个有序链表合并
     *
     * @return dd.code.test.ListNode
     * @Author zhangyong
     * @Description
     * @Date 15:52 2022/3/25
     * @Param [lists]
     **/
    ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 优先级队列，最⼩堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.data));
        // 将 k 个链表的头结点加⼊最⼩堆
        for (ListNode head : lists) {
            if (head != null) pq.add(head);
        }
        while (!pq.isEmpty()) {
            // 获取最⼩节点，接到结果链表中
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
           // p 指针不断前进
            p = p.next;
        }
        return dummy.next;
    }
}
