public class TestCalculate {
    public static void main(String[] args) {
        String question="81-8*9+12=";
        System.out.println("问题是： "+question);
        String calculate = Calculator.Calculate(question);
        System.out.println("结果为  "+calculate);
    }


}
