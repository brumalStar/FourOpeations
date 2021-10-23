import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TestTrees {

    public static HashMap<Integer, List<TreeNode>> map=new HashMap<>();//用来存放映射，检验是否有重复的式子
    public static void main(String[] args) {
        List<TreeNode> treeNodes;
        int size;
        TreeNode root;

        String question="81-8*9+12=";
        root=ConstructTree.buildTree(question);
//        System.out.println("问题是： "+question);
//        String calculate = Calculator.Calculate(question);
//        System.out.println("结果为  "+calculate);
        size=question.length();

        treeNodes=map.get(size);
        if(treeNodes==null)
        {
            map.put(size,new LinkedList<>());
            treeNodes=map.get(size);
        }
        treeNodes.add(root);
        map.put(size,treeNodes);


        String question1="81-9*8+12=";
        root=ConstructTree.buildTree(question1);
//        System.out.println("问题是： "+question1);
//        String calculate1 = Calculator.Calculate(question1);
//        System.out.println("结果为  "+calculate1);

        size=question1.length();

        for (TreeNode treeNode : treeNodes) {
            System.out.println("根1为  "+treeNode.getString());
            System.out.println("根2为  "+root.getString());
            //如果不存在就加入，并计算结果，如果存在就提前结束
            if (Compare.CompareTreeNode(root, treeNode))
            {

                System.out.println("存在式子一样的情况");
                return;
            }
        }
        System.out.println("不存在式子一样的情况");

        treeNodes.add(root);
        map.put(size,treeNodes);
        }
    }

