import java.io.IOException;
import java.util.Random;

public class Expression {


    static Random ran = new Random();
    private static final char[] symbol=new char[]{'+','-','*','÷'};

    public static void GetExpression(){//获得随机生成的表达式
        for(int i=1;i<=Main.OperatorNumber;i++)
        {
            String question=Getquestion()+"=";
            String result=Calculator.Calculate(question);

            try {
                String FinalQuestion="第"+i+"道题目: "+question;
                WriteUtil.write(Main.QuestionPath,FinalQuestion);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                String FinalResult="第"+i+"道题目: "+result;
//                WriteUtil.write(Main.QuestionPath,result);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
    }

    private static String Getquestion(){
        int operatorNum = ran.nextInt(3) + 1;//操作符号个数
        StringBuilder builder=new StringBuilder();
        int molecule=ran.nextInt(Main.range);
        int denominator=ran.nextInt(Main.range)+1;
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
            int position=ran.nextInt(4);

            if(symbol[position]=='÷'&&molecule==0){
                j--;
                continue;
            }

            builder.append(symbol[position]);
            if(AddLeftBracket(operatorNum,brackets,j)) builder.append("(");
            builder.append(Fraction.GetFraction(molecule,denominator));
            if(AddRightBracket(operatorNum,brackets,j)) builder.append(")");
        }
        //这里还需要补充在计算出结果后结果为负数的情况下的回退情况
        return builder.toString();
    }

    private static boolean AddLeftBracket(int operatorNum,int brackets,int j){
        if(operatorNum>1&&brackets==3&&j==0) return true;
        else if(operatorNum>2&&brackets==5&&j==1) return true;
        else return false;
    }

    private static boolean AddRightBracket(int operatorNum,int brackets,int j){
        if(operatorNum>1&&brackets==1&&j==0) return true;
        else if(operatorNum>1&&brackets==3&&j==1) return true;
        else if(operatorNum>2&&brackets==5&&j==2) return true;
        else return false;
    }



}
