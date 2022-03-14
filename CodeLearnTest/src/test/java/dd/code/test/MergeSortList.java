package dd.code.test;

import org.junit.Test;

/**java实现两个有序单链表合并
 * @Author zhangyong
 * @Description
 * @Date 19:49 2022/1/14 2022
 **/
public class MergeSortList {

    @Test
    public void test(){
        int[] a = {1, 2, 5, 7, 13, 21};
        int[] b = {1, 3, 4, 8, 13, 23, 28, 31, 51};
        ListNode listNode1 = ListNode.arrayToListNode(a);
        ListNode listNode2 = ListNode.arrayToListNode(b);
        ListNode listNode = mergeTwoLists(listNode1, listNode2);
        ListNode.printListNode(listNode);

//        ListNode reverse = ListNode.reverse(listNode1);
//        ListNode.printListNode(reverse);


    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0), p = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return dummy.next;

    }
    /**
     * 递归.
     * 1. 终止条件 l1 == null || l2 == null
     * 2. l1.next = mergeTwoLists
     * <p>
     * 时间复杂度：O(n+m),其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉l1或者l2的头结点(直到至少有一个链表为空)，
     * 函数(mergeTwoList)至多只会递归调用每个节点一次。因此时间复杂度取决于合并后的链表长度，即O(n+m)
     * 空间复杂度：O(n+m),其中 n 和 m 分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于
     * 递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为O(n+m)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.data <= l2.data) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }


}

class ListNode {
    /**
     * 为了方便，这两个变量都使用public，而不用private就不需要编写get、set方法了。
     * 存放数据的变量，简单点，直接为int型
     */
    public int data;
    /**
     * 存放结点的变量,默认为null
     */
    public ListNode next;

    /**
     * 构造方法，在构造时就能够给data赋值
     */
    public ListNode(int data) {
        this.data = data;
    }

    //数组转换成链表
    public static ListNode arrayToListNode(int[] s) {
        ListNode root = new ListNode(s[0]);//生成链表的根节点，并将数组的第一个元素的值赋给链表的根节点
        ListNode other = root;//生成另一个节点，并让other指向root节点，other在此作为一个临时变量，相当于指针
        for (int i = 1; i < s.length; i++) {//由于已给root赋值，所以i从1开始
            ListNode temp = new ListNode(s[i]);//每循环一次生成一个新的节点,并给当前节点赋值
            other.next = temp;//将other的下一个节点指向生成的新的节点
            other = temp;//将other指向最后一个节点(other的下一个节点)  other=other.getNext();
        }
        return root;
    }
    //将链表转换成数组
    public  static int[] listNodeToArray(ListNode l) {
        int size = listNodeSize(l);
        int[] ints = new int[size];
        int index = 0;
        while (l != null) {
            ints[index] = l.data;
            l = l.next;
            index++;
        }
        return ints;
    }
    //求链表的长度
    public  static int listNodeSize(ListNode l) {
        int size = 0;
        while (l != null) {
            size++;
            l = l.next;
        }
        return size;
    }

    //遍历一个链表
    public  static void printListNode(ListNode l) {
        while (l != null) {
            System.out.print(l.data + " ");
            l = l.next;
        }
    }
    /**
     * 反转一个链表 递归.
     * <p>
     * 1. 结束条件 head.next == null;
     * 2. 递推公式 head.next.next = head; head.next = null;
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n), 其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间，最多为 n 层。
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    /**K个一组翻转链表
     * @Author zhangyong
     * @Description
     * @Date 17:43 2022/3/13
     * @Param [head, k]
     * @return dd.code.test.ListNode
     **/
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        int count = 0;
        ListNode curr = head;
        while(count != k && curr != null){
            curr = curr.next;
            count++;
        }
        if(count == k){
            curr = reverseKGroup(curr,k); //上一次翻转后的头节点
            while(count -- > 0){ //翻转
                ListNode tmp = head.next;
                head.next = curr;
                curr = head;
                head = tmp;
            }
            head = curr;
        }
        return head;
    }

}
