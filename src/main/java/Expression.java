import java.io.IOException;
import java.util.Random;

public class Expression {


    static Random ran = new Random();
    private static char[] symbol=new char[]{'+','-','*','÷'};

    public static void GetExpression(){//获得随机生成的表达式
        for(int i=1;i<=Main.OperatorNumber;i++)   //先写10个测试一下
        {
            int operatorNum = ran.nextInt(3) + 1;//操作符号个数
            StringBuilder builder=new StringBuilder();
            int molecule=ran.nextInt(Main.range);
            int denominator=ran.nextInt(Main.range)+1;

            builder.append(Fraction.GetFraction(molecule,denominator));
            for(int j=0;j<operatorNum;j++){
                molecule=ran.nextInt(Main.range);
                denominator=ran.nextInt(Main.range)+1;
                int position=ran.nextInt(4);
                if(symbol[position]=='÷'&&molecule==0){
                    j--;
                    continue;
                }
                builder.append(symbol[position]+Fraction.GetFraction(molecule,denominator));
            }

            //这里还需要补充在计算出结果后结果为负数的情况下的回退情况
            String question=builder.toString();
            Calculator.Calculate(question);
            try {
                String result="第"+i+"道题目: "+question;
                WriteUtil.write(Main.QuestionPath,result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
