package dd.code.test;

import java.util.*;

/**
 * @Author zhangyong
 * @Description
 * @Date 15:11 2022/3/4 2022
 **/
public class BFSTest {

    public class TreeNode<V> {

        private V value;
        private List<TreeNode<V>> childList;//子节点列表

        public TreeNode(V value) {
            this.value = value;
        }

        public TreeNode(V value, List<TreeNode<V>> childList) {
            this.value = value;
            this.childList = childList;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public List<TreeNode<V>> getChildList() {
            return childList;
        }

        public void setChildList(List<TreeNode<V>> childList) {
            this.childList = childList;
        }


    }
    /**广度优先搜索算法（Breadth-First Search，BFS）
        广度优先搜索算法是从根节点开始，沿着树的宽度遍历树的节点。如果所有节点均被访问，则算法中止。
     * @Author zhangyong  递归算法
     * @Description
     * @Date 15:13 2022/3/4
     * @Param [children, depth]
     * @return void
     **/
    public static <V> void bfs(List<TreeNode<V>> children, int depth) {
        List<TreeNode<V>> thisChildren, allChildren = new ArrayList<>();
        for (TreeNode<V> child: children) {
            //打印节点值以及深度
            System.out.println(child.getValue().toString() + ",   " + depth);
            thisChildren = child.getChildList();
            if (thisChildren != null && thisChildren.size() > 0) {
                allChildren.addAll(thisChildren);
            }
        }
        if (allChildren.size() > 0)  {
            bfs(allChildren, depth + 1);
        }
    }

    /**非递归算法
     * @Author zhangyong
     * @Description
     * @Date 15:15 2022/3/4
     * @Param [tree]
     * @return void
     **/
    public static <V> void bfsNotRecursive(TreeNode<V> tree) {
        if (tree != null) {
            //跟上面一样，使用 Map 也只是为了保存树的深度，没这个需要可以不用 Map
            Queue<Map<TreeNode<V>, Integer>> queue = new ArrayDeque<>();
            Map<TreeNode<V>, Integer> root = new HashMap<>();
            root.put(tree, 0);
            queue.offer(root);
            while (!queue.isEmpty()) {
                Map<TreeNode<V>, Integer> itemMap = queue.poll();
                TreeNode<V> itemTreeNode = itemMap.keySet().iterator().next();
                int depth = itemMap.get(itemTreeNode);
                //打印节点值以及深度
                System.out.println(itemTreeNode.getValue().toString() + ",   " + depth);
                if (itemTreeNode.getChildList() != null &&
                        !itemTreeNode.getChildList().isEmpty()) {
                    for (TreeNode<V> child : itemTreeNode.getChildList()) {
                        Map<TreeNode<V>, Integer> map = new HashMap<>();
                        map.put(child, depth + 1);
                        queue.offer(map);
                    }
                }
            }
        }
    }




}
