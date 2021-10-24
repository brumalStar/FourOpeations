import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Compare {

//    用于比较答案之间的差异
    public static void CompareTxt(String path1, String path2){ //比较两个文件的不同  1：自己的答案（短） 2：计算出的答案（长）
        //用来存放正确答案的下标
        List<Integer> rightIndex = new ArrayList<>();
        //用来存放错误答案的下标
        List<Integer> errorIndex = new ArrayList<>();
      File file1=new File(path1);
      File file2=new File(path2);
      String text1;
      String text2;
      int count=1;

        try{
            String encoding="UTF-8";
            //判断两个文件是否符合要求
            if((file1.isFile() && file1.exists())&&(file2.isFile() && file2.exists())) { //判断读取的文件是否存在和是否为文件类型
                FileInputStream fileInputStream1 = new FileInputStream(file1);
                InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1, encoding);
                BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);

                FileInputStream fileInputStream2 = new FileInputStream(file2);
                InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2, encoding);
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
               //两个文件逐行进行比较
                while (((text1 = bufferedReader1.readLine()) != null)&&((text2 = bufferedReader2.readLine()) != null)) {
                    //如果匹配成功放入成功列，否则放入失败列
                    if(text1.equals(text2)){
                        rightIndex.add(count);
                    }
                    else{
                        errorIndex.add(count);
                    }
                    count++;
                }
                while(bufferedReader2.readLine() != null){ //未回答出来的也算错误；
                    errorIndex.add(count);
                    count++;
                }
                //将结果写入到目的文件
                WriteResult(rightIndex,true);
                WriteResult(errorIndex,false);

                //读取流关闭
                inputStreamReader1.close();
                bufferedReader1.close();
                fileInputStream1.close();

                inputStreamReader2.close();
                bufferedReader2.close();
                fileInputStream2.close();
            }
            else{
                System.out.println("文件不存在或者读取的不是文件");
            }
        }catch(IOException e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

//    将结果写入目标文件
    private static void WriteResult(List<Integer> list,boolean flag){
        StringBuilder builder=new StringBuilder();
        //判断当前是什么情况
        if(flag) builder.append("Correct: ");
        else builder.append("Wrong: ");
        int length=list.size();
        builder.append(Integer.valueOf(length)).append(" (");
        int temp;
        //将结果写入缓冲区
        for(int i=0;i<length;i++){
            temp= list.get(i);
            if(i<length-1)
            builder.append(Integer.valueOf(temp)).append(",");
            else
            builder.append(Integer.valueOf(temp));
        }
        builder.append(")");
        //将结果写入目的文件
        WriteUtil.write(Main.GradePath,builder.toString());
    }

//用于比较两棵树的情况
    public static boolean CompareTreeNode(TreeNode root1,TreeNode root2){
        //首先判断根节点是否为空
        if(root1==null&&root2==null) return true;
        if(root1==null||root2==null) return false;
//        System.out.println("root1----"+root1.getString());
//        System.out.println("root2----"+root2.getString());
        if(!root1.getString().equals(root2.getString())) return false;

//        根据乘法和加法的结合律判断当前情况是否是相同式子
        if(root1.getString().equals("* ")||root1.getString().equals("+ ")){

            return (CompareTreeNode(root1.getLeft(),root2.getLeft())&&CompareTreeNode(root1.getRight(),root2.getRight()))||
                    (CompareTreeNode(root1.getRight(),root2.getLeft())&&CompareTreeNode(root1.getLeft(),root2.getRight()));
        }

        return CompareTreeNode(root1.getLeft(),root2.getLeft())&&CompareTreeNode(root1.getRight(),root2.getRight());

    }



}
