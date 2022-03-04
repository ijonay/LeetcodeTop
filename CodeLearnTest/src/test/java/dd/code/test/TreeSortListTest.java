package dd.code.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @Author zhangyong
 * @Description
 * @Date 7:54 下午 2021/11/9 2021
 **/
@Slf4j
/**
 * 二叉树 前序 中序 后序遍历
 **/
public class TreeSortListTest {


    @Test
    public void climbTest(){


    }



}
class Tree {
    private int data;
    private Tree leftNode;
    private Tree rightNode;
    public Tree(int data, Tree leftNode, Tree rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Tree getLeftNode() {
        return leftNode;
    }
    public void setLeftNode(Tree leftNode) {
        this.leftNode = leftNode;
    }
    public Tree getRightNode() {
        return rightNode;
    }
    public void setRightNode(Tree rightNode) {
        this.rightNode = rightNode;
    }
    public void printNode(Tree node){
        System.out.print(node.getData());
    }
    /**
     * @author yaobo
     * 二叉树的先序中序后序排序
     */
    public Tree init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        Tree J = new Tree(8, null, null);
        Tree H = new Tree(4, null, null);
        Tree G = new Tree(2, null, null);
        Tree F = new Tree(7, null, J);
        Tree E = new Tree(5, H, null);
        Tree D = new Tree(1, null, G);
        Tree C = new Tree(9, F, null);
        Tree B = new Tree(3, D, E);
        Tree A = new Tree(6, B, C);
        return A;   //返回根节点
    }
    /**递归求解 前序 中序 后序遍历
     * @Author zhangyong
     * @Description
     * @Date 14:26 2022/1/22
     * @Param [root]
     * @return void
     **/
    public void theFirstTraversal(Tree root) {  //先序遍历
        printNode(root);
        if (root.getLeftNode() != null) {  //使用递归进行遍历左孩子
            theFirstTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null) {  //递归遍历右孩子
            theFirstTraversal(root.getRightNode());
        }
    }
    public void theInOrderTraversal(Tree root) {  //中序遍历
        if (root.getLeftNode() != null) {
            theInOrderTraversal(root.getLeftNode());
        }
        printNode(root);
        if (root.getRightNode() != null) {
            theInOrderTraversal(root.getRightNode());
        }
    }


    public void thePostOrderTraversal(Tree root) {  //后序遍历
        if (root.getLeftNode() != null) {
            thePostOrderTraversal(root.getLeftNode());
        }
        if(root.getRightNode() != null) {
            thePostOrderTraversal(root.getRightNode());
        }
        printNode(root);
    }

    /**用栈实现 前序 中序 后序遍历
     * @Author zhangyong
     * @Description
     * @Date 14:26 2022/1/22
     * @Param [root]
     * @return void
     **/
    public void theFirstTraversal_Stack(Tree root) {  //先序遍历
        Stack<Tree> stack = new Stack<>();
        Tree node = root;
        while (node != null || stack.size() > 0) {  //将所有左孩子压栈
            if (node != null) {   //压栈之前先访问
                printNode(node);
                stack.push(node);
                node = node.getLeftNode();
            } else {
                node = stack.pop();
                node = node.getRightNode();
            }
        }
    }

    public void theInOrderTraversal_Stack(Tree root) {  //中序遍历
        Stack<Tree> stack = new Stack<Tree>();
        Tree node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                stack.push(node);   //直接压栈
                node = node.getLeftNode();
            } else {
                node = stack.pop(); //出栈并访问
                printNode(node);
                node = node.getRightNode();
            }
        }
    }

    public void thePostOrderTraversal_Stack(Tree root) {   //后序遍历
        Stack<Tree> stack = new Stack<Tree>();
        Stack<Tree> output = new Stack<Tree>();//构造一个中间栈来存储逆后序遍历的结果
        Tree node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                output.push(node);
                stack.push(node);
                node = node.getRightNode();
            } else {
                node = stack.pop();
                node = node.getLeftNode();
            }
        }
        System.out.println(output.size());
        while (output.size() > 0) {

            printNode(output.pop());
        }
    }

    /**双端链表 前序
     * @Author zhangyong
     * @Description
     * @Date 16:08 2022/1/22
     * @Param [root]
     * @return java.util.List<java.lang.Integer>
     **/
    public List<Integer> postorderTraversal(Tree root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;

        Stack<Tree> toVisit = new Stack<>();
        toVisit.push(root);
        Tree cur;

        while (!toVisit.isEmpty()) {
            cur = toVisit.pop();
            result.addFirst(cur.data);
            if (cur.leftNode != null) toVisit.push(cur.leftNode);
            if (cur.rightNode != null) toVisit.push(cur.rightNode);
        }
        return result;
    }

    public static void main(String[] args) {

        LinkedList<String> queue = new LinkedList<String>();
        queue.add("qwe");
        queue.add("qwe1");
        queue.add("qwe2");
        queue.add("qwe3");
        queue.add(0,"sdfsd");
        System.out.printf("queue"+queue.toArray());


    }

    /**二叉树 层级遍历
     * @Author zhangyong
     * @Description
     * @Date 15:29 2022/3/4
     * @Param [root]
     * @return void
     **/
    public void levelIterator(Tree root) {
        if (root == null) {
            return;
        }
        LinkedList<Tree> queue = new LinkedList<Tree>();
        Tree current = null;
        queue.offer(root);//将根节点入队
        while (!queue.isEmpty()) {
            current = queue.poll();//出队队头元素并访问
            System.out.print(current.data + "-->");
            if (current.leftNode != null)//如果当前节点的左节点不为空入队
            {
                queue.offer(current.leftNode);
            }
            if (current.rightNode != null)//如果当前节点的右节点不为空，把右节点入队
            {
                queue.offer(current.rightNode);
            }
        }

    }

    /**层级遍历 从叶子节点开始
     * @Author zhangyong
     * @Description
     * @Date 15:44 2022/3/4
     * @Param [root]
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public List<List<Integer>> levelOrderBottom(Tree root) {
        Queue<Tree> queue = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            List<Integer> temp = new ArrayList<>();
            int count = queue.size();
            while (count-- > 0) {
                Tree node = queue.poll();
                if (node == null) {
                    continue;
                }
                temp.add(node.data);
                queue.add(node.leftNode);
                queue.add(node.rightNode);
            }
            if (temp.size() > 0) {
                result.add(0, temp);
            }
        }
        return result;
    }

}
