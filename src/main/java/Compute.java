public class Compute {

    public static void add(Fraction x,Fraction y){ //加

       Calculator.fractionStack.push(new Fraction(x.molecule * y.demoninator + y.molecule* x.demoninator, y.demoninator * x.demoninator));
    }

    public static void sub(Fraction x,Fraction y){ //减

        Calculator.fractionStack.push(new Fraction(x.molecule * y.demoninator - y.molecule * x.demoninator, y.demoninator * x.demoninator));
    }

    public static void mul(Fraction x,Fraction y){  //乘
        Calculator.fractionStack.push(new Fraction(x.molecule*y.molecule,x.demoninator*y.demoninator));
    }

    public static void div(Fraction x,Fraction y){  //除

       Calculator.fractionStack.push(new Fraction(y.demoninator*x.molecule,x.molecule*y.demoninator));
    }


    public static void revert(){// 处理 '


    }


}
