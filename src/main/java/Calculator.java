
import java.util.Stack;

public class Calculator {  //计算表达式
    //保留情况 HashSet<String> QuestionSet=new HashSet<String>();

    public static Stack<Character> operationStack = null;//用来存放符号
    public static Stack<Fraction> fractionStack = null;//用来存放分数
    public static Stack<Integer> NumStack = null;//用来存放数字
    public static boolean BiggerFlag=false;//用来判断当前计算是否出现负数


    public static String Calculate(String question) {
        //初始化
        question=removeStrSpace(question);
        BiggerFlag=false;
        fractionStack = new Stack<>();//
        operationStack = new Stack<>();
        NumStack = new Stack<>();

        StringBuilder nowFractionNum = new StringBuilder();
        int aux=0;//用来记录当前真分数的值
        int length = question.length();
        for (int i = 0; i < length; i++) {
            char temp = question.charAt(i);
            if (isNumber(temp)) nowFractionNum.append(temp);//如果是数字就放入到builder之中
            else if (temp=='(') {
                //括号情况(如果出现左括号，说明前面的数已经是完毕了的，就生成然后装填)
                    if(!NumStack.empty()) {
                        int demoninator = 1;
                        int molecule = NumStack.pop();
                        fractionStack.push((new Fraction(molecule, demoninator)));
                    }
                i=DealBracket(i,question);
                if(i==-1) return null;
            } else if (temp == '’') { //处理真分数的真值
                StringToNum(nowFractionNum);
                aux = NumStack.pop(); //得出真分数的真值；
            }else if(temp=='/'){
                operationStack.push('/');
                StringToNum(nowFractionNum);
            }
            else { //其他符号
                if(nowFractionNum.length()!=0)
                    StringToNum(nowFractionNum); //得出数字并且压入栈
                aux = convertMixed2Improper(aux);

                if (!NumStack.empty()) //如果分数的分母为1
                {
                    int demoninator=1;
                    int molecule=NumStack.pop();
                    fractionStack.push((new Fraction(molecule, demoninator)));
                }
                //优先计算乘除法
                if (fractionStack.size() > 1&&!operationStack.empty()&&(operationStack.peek()=='*'||operationStack.peek()=='÷')) {
                    operator();
                }
                //然后计算加减法
                if (fractionStack.size() > 1&&!operationStack.empty()&&(operationStack.peek()=='+'||operationStack.peek()=='-')) {
                    if(temp=='+'||temp=='-')
                    operator();
                }
                if(temp!='=')
                    operationStack.push(temp);//将符号压入栈
                else{
                    //最后处理剩余的符号
                    while(!operationStack.empty()){
                        operator();
                        if(BiggerFlag) return null;
                    }
                }
            }
        }
        //判断最后得出的结果是否唯一
        if (fractionStack.size() == 1) {
            //将最后的结果转为真分数形式
            Fraction result = fractionStack.pop();
            return Fraction.GetFraction(result.molecule,result.demoninator);
        }
        return null;
    }

    //去除空格
    private static String removeStrSpace(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    //处理括号
    public static int DealBracket(int point,String question){ //返回处理表达式的长度
        StringBuilder nowFractionNum1=new StringBuilder();

        int aux=0;
        for(int j=point+1;j<question.length();j++) {
            char temp = question.charAt(j);
            if (isNumber(temp)) nowFractionNum1.append(temp);
            else if (temp == '’') {
                StringToNum(nowFractionNum1);
                aux = NumStack.pop(); //得出真分数的真值；
            } else if (temp == '/') {
                operationStack.push('/');
                StringToNum(nowFractionNum1);
            } else { //其他符号
                StringToNum(nowFractionNum1); //得出数字并且压入栈

                aux=convertMixed2Improper(aux);

                if (!NumStack.empty()) {
                    int demoninator = 1;
                    int molecule = NumStack.pop();
                    fractionStack.push((new Fraction(molecule, demoninator)));
                }

                if (fractionStack.size() > 1&&temp==')') {
                    operator();
                    if (BiggerFlag) return -1;
                }
                if (temp != ')')
                {
                    operationStack.push(temp);
                }
                else {
                    return j;
                }
            }
        }
        return -1;
    }


    private static boolean isNumber(char x) { //判断是否为数字
        return x >= '0' && x <= '9';
    }



    private static void operator() {  //计算操作

        Fraction y = fractionStack.pop();//取出数字
        Fraction x = fractionStack.pop();
        switch (operationStack.pop()) {//根据符号进行运算
            case '+':
                Compute.add(x, y);
                break;
            case '-':
                Compute.sub(x, y);
                if(!isBigger())  BiggerFlag=true;//如果计算出负数就设置为true
                //System.out.println("计算过程不能产生负数");
                break;
            case '*':
                Compute.mul(x, y);
                break;
            case '÷':
                Compute.div(x, y);
                break;
            default:
                break;
        }

        //System.out.println("结果的分子 "+result.molecule+"分母为 "+result.demoninator);
    }

    private static void StringToNum(StringBuilder nowFractionNum) { //将缓存的字符串转为数字
        String checkFractionNum = nowFractionNum.toString(); // 将数字缓存转为字符串
        int num = Integer.parseInt(checkFractionNum); // 将数字字符串转为长整型数
        NumStack.push(num);
        nowFractionNum.delete(0, nowFractionNum.length());//清空缓冲区
    }


    private static boolean isBigger() {  //用来判断分数是否为负，加在减法运算之后
        Fraction x=fractionStack.peek();
        return x.demoninator * x.molecule >= 0;
    }

    public static int convertMixed2Improper(int aux){//处理真分数，转为分数
        if (!operationStack.empty()&&operationStack.peek() == '/') {
            operationStack.pop();
            int demoninator = NumStack.pop();
            int molecule = NumStack.pop();
            molecule += aux * demoninator;
            fractionStack.push(new Fraction(molecule, demoninator));
            aux = 0;
        }
        return aux;
    }

}


