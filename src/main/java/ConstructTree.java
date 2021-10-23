import java.util.ArrayList;

public class ConstructTree {


    public static TreeNode buildTree(String str){

        //分别存放数字和操作符
        ArrayList<TreeNode> numbers = new ArrayList<>();
        ArrayList<TreeNode> operations = new ArrayList<>();
        TreeNode root=new TreeNode();
        TreeNode binaryNode;
        StringBuilder temp = new StringBuilder();
        boolean flag1=false;


        for (int i = 0; i < str.length(); i++) {
            //1 取出字符串的各个字符
            char ch = str.charAt(i);
            //2 判断为符号还是数字，若为数字，则将s+=ch（防止数字为十位百位数）
            if (ch >= '0' && ch <= '9') {
                 temp.append(ch);
            }else if(ch=='('||ch==')'){
                if(ch==')') flag1=true;
            }
            //3 若为运算符，则将s和ch分别放入numbers、operations数组队列
            else {
                numbers.add(new TreeNode(temp.toString()));
//                这是处理/与括号的情况
                if(flag1){
                    binaryNode=operations.get(operations.size()-1);
                    operations.remove(operations.size()-1);

                    if(numbers.get(numbers.size()-1)!=null){
                        binaryNode.setRight(numbers.get(numbers.size()-1));
                        numbers.remove(numbers.size()-1);
                    }
                    else binaryNode.setRight(null);
//                    System.out.println("右节点值为"+binaryNode.getRight().getString());


                    if(numbers.get(numbers.size()-1)!=null){
                        binaryNode.setLeft(numbers.get(numbers.size()-1));
                        numbers.remove(numbers.size()-1);
                    }
                    numbers.add(numbers.size(), binaryNode);
                    flag1=false;
                }
                operations.add(new TreeNode(ch + " "));
                if(ch=='/'||ch=='’') flag1=true;
                temp = new StringBuilder();
            }
        }

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
//            System.out.println("左节点值为"+binaryNode.getLeft().getString());

            if(numbers.get(0)!=null){
                binaryNode.setRight(numbers.get(0));
                numbers.remove(0);
            }
            else binaryNode.setRight(null);

//            System.out.println("右节点值为"+binaryNode.getRight().getString());

            //3构建node,将其作为根节点root放回数字列表
            root = binaryNode;
//            System.out.println("根节点的值为"+binaryNode.getString());
            numbers.add(0, binaryNode);
        }
        return root;
    }



}
