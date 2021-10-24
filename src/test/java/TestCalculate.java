import org.junit.Test;

public class TestCalculate {

    @Test
    public void testAdd() {
        String question="( 3 + 1 ) + ( 4 + 3 ) =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testSub() {
        String question="( 3 - 2 ) - ( 1 - 1 ) =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testDiv() {
        String question="( 3 ÷ 4 )÷ 2 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }
    @Test
    public void testMul() {
        String question="( 3 * 12 ) * 1=";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testFraction() {
        String question="36 / 14 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testFractionAdd() {
        String question=" 36 / 14 + 120 / 31 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testFractionSub() {
        String question=" 360 / 14 - 120 / 31 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testFractionDiv() {
        String question=" 360 / 14 ÷ 120 / 31 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testFractionMul() {
        String question=" 361 / 14 * 119 / 31 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testFour(){
        String question=" (11 / 14 * 11 / 31) + 4/19 - 1/99 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testMixed() {
        String question=" 1’1/4 * 3 ÷ (3+8-4) =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }

    @Test
    public void testZero() {
        String question=" 4/0 =";
        String question1="3 ÷ 0=";
        System.out.println("结果为  "+ Calculator.Calculate(question));
        System.out.println("结果为  "+ Calculator.Calculate(question1));
    }

    @Test
    public void testNeg(){
        String question=" 3-4 =";
        System.out.println("结果为  "+ Calculator.Calculate(question));
    }






}
