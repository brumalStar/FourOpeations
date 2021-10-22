import java.util.Scanner;

public class Main {
    public static final String QuestionPath="C:/Users/zyw12/Desktop/question.txt";
    public static final String AnswerPath="C:/Users/zyw12/Desktop/answers.txt";

    public static int range;//数值的取值范围
    public static int OperatorNumber;//生成表达式的条数

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数值的取值范围: ");
        range=sc.nextInt();
        System.out.println("请输入表达式的生成条数: ");
        OperatorNumber=sc.nextInt();
        Expression.GetExpression();

    }
}
