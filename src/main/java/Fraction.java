public class Fraction {

    public int molecule;//分子
    public int demoninator;//分母

    public Fraction(int molecule, int demoninator) { //定义结构体
        this.molecule = molecule;
        this.demoninator = demoninator;
    }


    public static String GetFraction(int molecule, int denominator){
        if(denominator==0) {//判断当前分母是否为0
           Calculator.BiggerFlag=true;
           return null;
        }
        molecule=Math.abs(molecule);//求绝对值
        denominator=Math.abs(denominator);
        int x=gcd(molecule, denominator);//约分
        molecule/=x;
        denominator/=x;

        int temp=molecule/denominator;//真分数
        //求分子的最小值
        molecule%=denominator;
        //如果分子是分母的整数倍
        if(molecule==0) return temp+"";
        String result= molecule +"/"+ denominator;
        if(temp==0){
            return result;
        }else{
            return temp +"’"+result;
        }
    }

    private static int gcd(int a,int b) {//约分两个数
        return (b==0)?a:gcd(b,a%b);
    }
}
