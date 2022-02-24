package dd.code.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author zhangyong
 * @Description
 * @Date 16:32 2022/2/24 2022
 **/
public class mergeSortArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,6,6,6},num2 = new int[]{5,6,7};
        int[] ints = merge4(nums1, 3, num2, 3);
        System.out.println(Arrays.toString(ints));
//        //去重的情况
//        int[] ans = merge4(nums1, 3, num2, 3);
//        System.out.println(Arrays.toString(ans));
    }

    //方式一:直接将nums2全部放到num1的尾部 然后直接排序(没什么技术含量)
    public static void merge(int[] nums1,int m,int[] nums2,int n){
        for (int i=0;i<n;i++){
            nums1[i+m] = nums2[i];
        }
        Arrays.sort(nums1);
    }
    //方式二:双指针 然后开创一个新数组,将排序结果放到新数组中,最后再移到nums1中
    public static void merge2(int[] nums1,int m,int[] nums2,int n){
        int p1 = 0,p2 = 0;
        int[] sort = new int[m+n];
        int temp;
        while(p1<m||p2<n){
            if (p1==m){
                temp = nums2[p2];
                p2++;
            }else if (p2==n){
                temp = nums1[p1];
                p1++;
            }else if(nums1[p1]<=nums2[p2]){
                temp = nums1[p1];
                p1++;
            }else{
                temp = nums2[p2];
                p2++;
            }

            sort[p1+p2-1] = temp;
        }

        nums1 = Arrays.copyOf(sort,sort.length);
    }
    //方式三:双指针 但是是倒序的 这样可以保证直接在nums1中进行操作而不会覆盖前面的nums1中的元素(但是这个时候双指针的排序是倒着从大到小排)
    public static void merge3(int[] nums1,int m,int[] nums2,int n){
        int p1 = m-1,p2 = n-1;
        int temp;
        while (p1>=0 || p2>=0){
            if(p1==-1){
                temp = nums2[p2];
                p2--;
            }else if (p2==-1){
                temp = nums1[p1];
                p1--;
            }else if(nums1[p1]>nums2[p2]){
                temp = nums1[p1];
                p1--;
            }else{
                temp = nums2[p2];
                p2--;
            }

            nums1[p1+p2+2] = temp;
        }
    }

    //进阶问题:合并链表并去重,还是利用双指针,只是在nums1[p1]==nums2[p2]条件时,让p1指针直接往后移动即可
    public static int[] merge4(int[] nums1,int m,int[] nums2,int n){
        List<Integer> list = new ArrayList<>();
        int p1 = 0,p2 = 0;
        while(p1<m||p2<n){
            if (p1==m){
                list.add(nums2[p2]);
                p2++;
            }else if(p2==n){
                list.add(nums1[p1]);
                p1++;
            }else if(nums1[p1]==nums2[p2]){
                p1++;
            }else if(nums1[p1]<nums2[p2]){
                list.add(nums1[p1]);
                p1++;
            }else{
                list.add(nums2[p2]);
                p2++;
            }
        }

        int[] ans = new int[list.size()];
        for (int i=0;i<list.size();i++){
            ans[i] = list.get(i);
        }
        return ans;
    }


}
