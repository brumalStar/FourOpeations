import javax.swing.*;

public class Main {
    public static final String QuestionPath="Exercises.txt";
    public static final String AnswerPath="Answers.txt";
    public static final String GradePath="Grade.txt";
    //是自己写的答案所在的文档
    //public static final String CopyPath="MyAnswer.txt";

    public static int range;//数值的取值范围
    public static int OperatorNumber;//生成表达式的条数



    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入数值的取值范围: ");
//        range=sc.nextInt();
//        System.out.println("请输入表达式的生成条数: ");
//        OperatorNumber=sc.nextInt();
//        Expression.GetExpression();
//        System.out.println("生成完毕");
//     Compare.CompareTxt(Main.CopyPath,Main.AnswerPath);

        OperationFrame frame = new OperationFrame();
        frame.setSize(500,500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
