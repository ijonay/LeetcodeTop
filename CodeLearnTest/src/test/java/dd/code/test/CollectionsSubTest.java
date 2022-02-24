package dd.code.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**求一个集合的所有子集
 * @Author zhangyong
 * @Description
 * @Date 14:00 2022/2/24 2022
 **/
public class CollectionsSubTest {
    //https://github.com/afatcoder/LeetcodeTop.git

    @Test
    public void test(){
        int[] a = {1,2,4,3,7};
        List<List<Integer>> resultList = new ArrayList<>();
//        subList(a, 0,resultList ,new ArrayList<>());
////
//        System.out.printf("resultList"+resultList.toString());
        List<List<Integer>> subCollections = getSubCollections(a);

        System.out.printf("subCollections"+subCollections.toString());

    }
    public List<List<Integer>> getSubCollections(int[] a){
        int[] b = new int[8];
        for(int aa: a){
            b[aa]=1;
        }
        List<List<Integer>> totalCol = new ArrayList<>();
        subList2(b, 0,totalCol ,new ArrayList<>());
        return totalCol;
    }
    /**
     * @description
     *	递归
     * @author TianwYam
     * @date 2021年6月1日下午3:22:41
     * @param list
     * @param index
     * @param resultList
     * @param subList
     */
    public static void subList2(int[] list , int index,
                               List<List<Integer>> resultList, List<Integer> subList) {

        resultList.add(new ArrayList<>(subList));

        int size = list.length ;
        for (int i = index; i < size; ) {
            if(list[i] == 1){
                subList.add(i);
                subList2(list, ++i, resultList, subList);
                if(!subList.isEmpty()){
                    subList.remove(subList.size() - 1);
                }
            }else{
                i++;
            }

        }

    }
    /**
     * @description
     *	递归
     * @author TianwYam
     * @date 2021年6月1日下午3:22:41
     * @param list
     * @param index
     * @param resultList
     * @param subList
     */
    public static void subList(int[] list , int index,
                               List<List<Integer>> resultList, List<Integer> subList) {

        resultList.add(new ArrayList<>(subList));

        int size = list.length ;
        for (int i = index; i < size; ) {
            subList.add(list[i]);
            subList(list, ++i, resultList, subList);
            subList.remove(subList.size() - 1);
        }

    }


}
