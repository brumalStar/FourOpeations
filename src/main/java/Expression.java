import java.io.IOException;
import java.util.*;

public class Expression {


    static Random ran = new Random();
    private static final char[] symbol=new char[]{'+','-','*','÷'};
    public static HashMap<Integer, List<TreeNode>> map=new HashMap<>();//用来存放映射，检验是否有重复的式子

    public static void GetExpression(){//获得随机生成的表达式
        int i=1;
        boolean flag=false;
           //根据输入生成指定数目的表达式
           while(i<=Main.OperatorNumber) {

               String question = GetQuestion() + "=";
               TreeNode root= ConstructTree.buildTree(question);
               int size=question.length();
               if(question.contains("(")){
                   size-=2;
               }

               List<TreeNode> treeNodes=map.get(size);
               if(treeNodes==null)
               {
                   map.put(size,new LinkedList<>());
                   treeNodes=map.get(size);

               }

                  else{
                      for (TreeNode treeNode : treeNodes) {
                          //如果不存在就加入，并计算结果，如果存在就提前结束
                          if (Compare.CompareTreeNode(root, treeNode))
                          {
                              flag=true;
                              break;
                          }
                      }

                      if(flag) {
                          flag=false;
                          continue;
                      }

                  }
               treeNodes.add(root);
               map.put(size,treeNodes);



               String result = Calculator.Calculate(question);
              //如果结果不为null（即符合题目要求）

               if (result != null) {
                   try {
                       String FinalQuestion = "第" + i + "道题目: " + question;
                       WriteUtil.write(Main.QuestionPath, FinalQuestion);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   try {
                       String FinalResult = "第" + i + "道答案: " + result;
                       WriteUtil.write(Main.AnswerPath, FinalResult);
                   } catch (IOException e) {
                       e.printStackTrace();
                   } finally {
                       i++;
                   }
               }
           }
    }

    //获取表达式
    private static String GetQuestion(){
        int operatorNum = ran.nextInt(3) + 1;//操作符号个数
        StringBuilder builder=new StringBuilder();
        int molecule=ran.nextInt(Main.range);//分子
        int denominator=ran.nextInt(Main.range)+1;//分母，分母不能为0
        int brackets = 0;
        //判断添加括号 (0,2,4是不生成括号，1,3,5是生成括号)
        if(operatorNum>1) brackets= ran.nextInt(6);

        if(operatorNum>1&&brackets==1) {
            builder.append("(");
        }

        builder.append(Fraction.GetFraction(molecule,denominator));
        for(int j=0;j<operatorNum;j++){
            molecule=ran.nextInt(Main.range);
            denominator=ran.nextInt(Main.range)+1;
            int position=ran.nextInt(4);//运算符号在symbol数组中的位置

            if(symbol[position]=='÷'&&molecule==0){//除号后面不能为0
                j--;
                continue;
            }

            builder.append(symbol[position]);
            if(AddLeftBracket(operatorNum,brackets,j)) builder.append("(");
            builder.append(Fraction.GetFraction(molecule,denominator));
            if(AddRightBracket(operatorNum,brackets,j)) builder.append(")");
        }

        return builder.toString();
    }

    //添加左括号的判断条件
    private static boolean AddLeftBracket(int operatorNum,int brackets,int j){
        if(operatorNum>1&&brackets==3&&j==0) return true;
        else if(operatorNum>2&&brackets==5&&j==1) return true;
        else return false;
    }

    //添加右括号的判断条件
    private static boolean AddRightBracket(int operatorNum,int brackets,int j){
        if(operatorNum>1&&brackets==1&&j==0) return true;
        else if(operatorNum>1&&brackets==3&&j==1) return true;
        else if(operatorNum>2&&brackets==5&&j==2) return true;
        else return false;
    }



}
