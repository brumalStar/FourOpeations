public class Compute {

    //加法
    public static void add(Fraction x,Fraction y){ //加

       Calculator.fractionStack.push(new Fraction(x.molecule * y.demoninator + y.molecule* x.demoninator, y.demoninator * x.demoninator));
    }

    //减法
    public static void sub(Fraction x,Fraction y){ //减

        Calculator.fractionStack.push(new Fraction(x.molecule * y.demoninator - y.molecule * x.demoninator, y.demoninator * x.demoninator));
    }

    //乘法
    public static void mul(Fraction x,Fraction y){  //乘
        Calculator.fractionStack.push(new Fraction(x.molecule*y.molecule,x.demoninator*y.demoninator));
    }

    //除法
    public static void div(Fraction x,Fraction y){  //除
       Calculator.fractionStack.push(new Fraction(y.demoninator*x.molecule,y.molecule*x.demoninator));
    }




}
