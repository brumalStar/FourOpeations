import org.junit.Test;

public class TestExpression {

    @Test
    public void testGenerateExpression(){
        Main.range = 1;
        Main.OperatorNumber = 10;
        Expression.GetExpression();
    }
}
