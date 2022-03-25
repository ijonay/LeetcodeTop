package dd.code.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.util.BitSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @Author zhangyong
 * @Description
 * @Date 10:59 上午 2021/11/9 2021
 **/
@Component
@Slf4j
/**
 *无序整数数组中找第k大的数
 **/
public class TopKTest {


    @Test
    public void Test1(){
        BitSet set = new BitSet();
        int[] array = {1,3,9,2,4,5,12,34,8,7};
        ThreadLocal a = new ThreadLocal();
//        int kv = kv(array, 3);
//        log.info("kv(array, 3):{}",kv);
//        quickModify(array,1,9,3);
//        log.info("quickModify:{}",array);
//        int[] ints = smallestK(array, 3);
//        log.info("smallestK:{}",ints);
          find(array, 3);
          log.info("5/2={}",5/2);
//        log.info("smallestK:{}",ints);
    }
    /**
     *四：构建最小堆，获取出最大k个元素
     **/
    private void find(int[] array,int k){
        int[] result=new int[k];
        for (int i=0;i<k;i++){
            result[i]=array[i];
        }
        createHeap(result);
        for (int i=k;i<array.length;i++){
            if (array[i]>result[0]){
                result[0]=array[i];
                adjustHeap(result,0);
            }
        }
        log.info("result:{}",result);
    }

    //创建一个最小堆
    private void createHeap(int[] array){
        for (int i=array.length/2;i>=0;i--){
            adjustHeap(array,i);
        }
    }

    //调整最大堆
    private void adjustHeap(int[] array,int s){
        int child=2*s+1;

        while (child<array.length){
            if (child+1<array.length&&array[child]>array[child+1]){
                child++;
            }
            if (array[s]>array[child]){
                ArrayUtils.swap(array,s,child);
                s=child;
                child=2*s+1;
            }else{
                break;
            }
        }
    }

    /**
     *一 键值索引方法：将每个数作为辅助数组的索引，计算每个数出现的次数。统计所有的次数，找到第K个数。
     * 时间复杂度：O(n)。
     * 空间复杂度：O(maxvalue(nums)),适用于数据的取值范围不太大的情景。内存多的话，空间换时间也可以。
     **/
    public int kv (int[] nums, int k) {
        //数组长度自定义，nums的最大值就是长度
        int[] kv = new int[35];
        for (int num: nums) {
            kv[num]++;
        }
        int sum = 0;
        int res = 0;
        for (int i = 0; i < 20; i++) {
            sum += kv[i];
            if (sum > nums.length - k) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     *解法三：改进的快速排序方法：避免对所有的数排序，利用快速排序分堆，然后递归另外一半（不需要两半都递归），直到最终所有小于基准数的个数为K【尚可】
     * 平均时间复杂度 O（N *logK）
     *
     * 快排中的每次递归，将待排数据分做两组，其中一组的数据的任何一个数都比另一组中的任何一个大，然后再对两组分别做类似的操作；
     * 在本问题中，假设 N 个数存储在数组 S 中，我们从数组 S 中随机找出一个元素 X，把数组分为两部分 Sa 和 Sb。Sa 中的元素大于等于 X，Sb 中元素小于 X。
     * 这时，有两种可能性：
     * 1. Sa中元素的个数小于K，Sa中所有的数和Sb中最大的K-|Sa|个元素（|Sa|指Sa中元素的个数）就是数组S中最大的K个数。
     * 2. Sa中元素的个数大于或等于K，则需要返回Sa中最大的K个元素。
     **/
    int res = 0;
    public void quickModify(int[] src, int begin, int end, int k ) {
        if (begin < end) {
            int key = src[begin];
            int i = begin;
            int j = end;
            while (i < j){
                while(i < j && src[j] < key){
                    j--;
                }
                if (i < j){
                    swap(src, i, j);
                    i++;
                }
                while (i < j && src[i] > key) {
                    i++;
                }
                if (i < j){
                    swap(src, i, j);
                    j--;
                }
            }
            if (i > k - 1) {
                quickModify(src, begin, i - 1, k);
            }
            if (i == k - 1) {
                res = src[i];
            }
            if (i < k - 1) {
                quickModify(src, i - 1, end, k - i);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     *方法2：构建小顶堆。
     *
     * 时间复杂度： O(n + kLogn)， 其中建初始堆: O(n)，取top: O(kLogn)
     *
     * 空间复杂度：O(1)
     **/
    public int[] smallestK(int[] arr, int k) {
        int len = arr.length;
        if (k >= len) {
            return arr;
        }
        if (k ==0) return new int[0];

        buildMinHeap(arr, len);

        int pos = len - k;
        //只对最小堆的前k个进行heapify，此时返回的数组中最后k个为先大后小排列，最后一个元素最小
        for (int i = len - 1; i >= pos; i--) {
            //将根节点与最后一个元素换位置，砍断最后一个节点；然后对剩下的节点进行heapify
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }

        int[] ret = new int[k];
        int j = 0;
        //倒着将arr中的元素写到返回结果中
        for (int i = len - 1; i >= pos; i--) {
            ret[j++] = arr[i];
        }

        return ret;
    }

    private void buildMinHeap(int[] arr, int len) {
        for (int i = (len - 1) / 2; i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    private void heapify(int[] arr, int i, int len) {
        if (i >= len) return;

        int min = i;
        int c1 = 2 * i + 1;
        int c2 = 2 * i + 2;

        if (c1 < len && arr[c1] < arr[min]) {
            min = c1;
        }
        if (c2 < len && arr[c2] < arr[min]) {
            min = c2;
        }

        if (min != i) {
            swap(arr, i, min);
            heapify(arr, min, len);
        }
    }

    /**快速排序
     * @Author zhangyong
     * @Description
     * @Date 16:45 2022/2/24
     * @Param
     * @return
     **/
    @Test
    public void quickSort() {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        quick(a);
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }

    public int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];    //数组的第一个作为中轴
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }
            list[low] = list[high];   //比中轴小的记录移到低端
            while (low < high && list[low] <= tmp) {
                low++;
            }
            list[high] = list[low];   //比中轴大的记录移到高端
        }
        list[low] = tmp;              //中轴记录到尾
        return low;                   //返回中轴的位置
    }

    public void _quickSort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);  //将list数组进行一分为二
            _quickSort(list, low, middle - 1);        //对低字表进行递归排序
            _quickSort(list, middle + 1, high);       //对高字表进行递归排序
        }
    }

    public void quick(int[] a2) {
        if (a2.length > 0) {    //查看数组是否为空
            _quickSort(a2, 0, a2.length - 1);
        }
    }


    /**优先队列 实现topK 问题的解决
     * @Author zhangyong
     * @Description
     * @Date 14:35 2022/3/3
     * @Param
     * @return
     **/
    public static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    public static  int K = 10;

    public static void add(int i) {
        if (queue.size() < K) {
            queue.add(i);
        } else {
            // Min heap
            int min = queue.peek();
            if (i > min) {
                queue.poll();
                queue.add(i);
            }
        }
    }

    public static void print() {
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        Random r = new Random();
        String a ="dfgsd";
        int i1 = a.offsetByCodePoints(2, 2);
//        Stream.of("Hello world!")
//
//                .map(Scanner::new)
//
//                .flatMap(s -> s.findAll("l"))
//
//                .map(mr -> mr.start())
//
//                .forEach(System.out::println);
        int fromIndex = getFromIndex("sfdsfsfsfsd", "sf", 2);
        System.out.println(""+fromIndex);
    }

    //子字符串modelStr在字符串str中第count次出现时的下标
    public static int getFromIndex(String str, String modelStr, Integer count) {
        //对子字符串进行匹配
        Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
        int index = 0;
        //matcher.find();尝试查找与该模式匹配的输入序列的下一个子序列
        while(slashMatcher.find()) {
            index++;
            //当modelStr字符第count次出现的位置
            if(index == count){
                break;
            }
        }
        //matcher.start();返回以前匹配的初始索引。
        return slashMatcher.start();
    }

}
