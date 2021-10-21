public class Fraction {

    public static String GetFraction(int molecule,int denominator){
        if(denominator==0) {
            throw new RuntimeException("分母不能为0");
        }
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
            return temp +"'"+result;
        }
    }

    private static int gcd(int a,int b) {//约分两个数
        return (b==0)?a:gcd(b,a%b);
    }
}
