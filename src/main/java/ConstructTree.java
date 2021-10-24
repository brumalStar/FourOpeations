import java.util.ArrayList;

public class ConstructTree {

//为每个式子建造一个二叉树
    public static TreeNode buildTree(String str){

        //        分别存放数字和操作符
        ArrayList<TreeNode> numbers = new ArrayList<>();
        ArrayList<TreeNode> operations = new ArrayList<>();
        //初始化一个根节点
        TreeNode root=new TreeNode();
        TreeNode binaryNode;
        StringBuilder temp = new StringBuilder();
        int flag1=0;


        for (int i = 0; i < str.length(); i++) {
            // 取出字符串的各个字符
            char ch = str.charAt(i);
            //判断为符号还是数字，若为数字，则将s+=ch（防止数字为十位百位数）
            if (ch >= '0' && ch <= '9') {
                 temp.append(ch);
            }else if(ch=='('||ch==')'){
                if(ch==')') flag1++;
            }else if(ch=='/'||ch=='’'){
                numbers.add(new TreeNode(temp.toString()));
                flag1++;
                operations.add(new TreeNode(ch + " "));
                temp = new StringBuilder();
            }
            //若为运算符，则将s和ch分别放入numbers、operations数组队列
            else {
                numbers.add(new TreeNode(temp.toString()));
//                这是处理/与),*和÷的情况
               if(flag1>0){
                   root=FirstBuild(flag1,numbers,operations,root);
                   flag1=0;
               }
                operations.add(new TreeNode(ch + " "));
                if(ch=='*'||ch=='÷') flag1++;

                temp = new StringBuilder();
            }
        }
        if(flag1>0)
            root=FirstBuild(flag1,numbers,operations,root);


//      再处理剩下的符号和数字
        while(operations.size()>1) {
            // 从运算符中取出第一个作为node的数据；
            binaryNode = operations.get(0);
            operations.remove(0);
            //从数字取出第一个、第二个作为左、右；
            if(numbers.get(0)!=null){
                binaryNode.setLeft(numbers.get(0));
                numbers.remove(0);
            }
            else binaryNode.setLeft(null);
         //   System.out.println("左节点值为"+binaryNode.getLeft().getString());

            if(numbers.get(0)!=null){
                binaryNode.setRight(numbers.get(0));
                numbers.remove(0);
            }
            else binaryNode.setRight(null);

         //  System.out.println("右节点值为"+binaryNode.getRight().getString());

            //构建node,将其作为根节点root放回数字列表
            root = binaryNode;
           // System.out.println("根节点的值为"+binaryNode.getString());
            numbers.add(0, binaryNode);
        }
//        返回根节点
        return root;
    }


    private static TreeNode FirstBuild(int flag1,ArrayList<TreeNode> numbers,ArrayList<TreeNode> operations,TreeNode root){

        TreeNode binaryNode;
        while (flag1>0){
            if(operations.size()<1) break;
//                    首先取出最后操作符，然后先构建一个树，再加入到List中
            binaryNode=operations.get(operations.size()-1);
            operations.remove(operations.size()-1);

//                   取出右节点（若为空，设置为空）
            if(numbers.get(numbers.size()-1)!=null){
                binaryNode.setRight(numbers.get(numbers.size()-1));
                numbers.remove(numbers.size()-1);
            }
            else binaryNode.setRight(null);
                  //System.out.println("右节点值为"+binaryNode.getRight().getString());

//                    取出左节点（若为空，设置为空）
            if(numbers.get(numbers.size()-1)!=null){
                binaryNode.setLeft(numbers.get(numbers.size()-1));
                numbers.remove(numbers.size()-1);
            }

            root=binaryNode;
            numbers.add(numbers.size(), binaryNode);
            flag1-=1;
        }
        return root;
    }



}
