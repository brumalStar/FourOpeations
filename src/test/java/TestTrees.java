import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class TestTrees {

    public static HashMap<Integer, List<TreeNode>> map=new HashMap<>();//用来存放映射，检验是否有重复的式子

        @Test
        public void testDuplicate() {
            List<TreeNode> treeNodes;
            int size;
            TreeNode root;

            String question="81-9*8+6=";
            root=ConstructTree.buildTree(question);


            size=question.length();
            treeNodes=map.get(size);
            if(treeNodes==null)
            {
                map.put(size,new LinkedList<>());
                treeNodes=map.get(size);
            }
            treeNodes.add(root);
            map.put(size,treeNodes);


            String question1="81-8*9+6=";
            root=ConstructTree.buildTree(question1);


            size=question1.length();
            treeNodes=map.get(size);
            for (TreeNode treeNode : treeNodes) {
                if(root.getString().equals(treeNode.getString())) {
                    //如果不存在就加入，并计算结果，如果存在就提前结束
                    if (Compare.CompareTreeNode(root, treeNode)) {
                        System.out.println("存在式子一样的情况");
                        return;
                    }
                }
            }
            System.out.println("不存在式子一样的情况");

            treeNodes.add(root);
            map.put(size,treeNodes);
        }
}

