public class TreeNode {

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

    public String getString() {
        return value;
    }

    public void setString(String value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }



}
