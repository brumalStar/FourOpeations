public class TreeNode {

//    分别表示值，左节点，右节点
    private String value;
    private TreeNode left;
    private TreeNode right;

//    有参构造
    public TreeNode(String value){
        this.value = value;
    }
//    无参构造
    public TreeNode(){
    }

    //返回值和设置值
    public String getString() {
        return value;
    }

    public void setString(String value) {
        this.value = value;
    }

//返回左节点和设置左节点
    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }
//返回右节点和设置右节点
    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }



}
