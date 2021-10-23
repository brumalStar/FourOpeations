import java.util.Stack;

public class Calculator {  //计算表达式
    //保留情况 HashSet<String> QuestionSet=new HashSet<String>();

    public static Stack<Character> operationStack = null;//用来存放符号
    public static Stack<Fraction> fractionStack = null;//用来存放分数
    public static Stack<Integer> NumStack = null;
    public static boolean BiggerFlag = false;
    public static Stack<Character> bracketStack= null;


    public static String Calculate(String question) {
        fractionStack = new Stack<Fraction>();//
        operationStack = new Stack<Character>();
        NumStack = new Stack<Integer>();
        //用来存放括号
        bracketStack = new Stack<Character>();
        StringBuilder nowFractionNum = new StringBuilder();
        int length = question.length();
        int aux = 0;
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
            } else if (temp == '’') {
                StringToNum(nowFractionNum);
                aux = NumStack.pop(); //得出真分数的真值；

            }else if(temp=='/'){
                operationStack.push('/');
                StringToNum(nowFractionNum);
            }
            else { //其他符号
                if(nowFractionNum.length()!=0)
                    StringToNum(nowFractionNum); //得出数字并且压入栈
                if (!operationStack.empty()&&operationStack.peek() == '/') {
                    operationStack.pop();
                    int demoninator = NumStack.pop();
                    int molecule = NumStack.pop();
                    molecule += aux * demoninator;
                    fractionStack.push(new Fraction(molecule, demoninator));
                    aux = 0;
                }

//                if(temp!='=')
//                    operationStack.push(temp);//将符号压入栈

                if (!NumStack.empty())
                {
                    int demoninator=1;
                    int molecule=NumStack.pop();

                    fractionStack.push((new Fraction(molecule, demoninator)));
                }
                if (fractionStack.size() > 1&&!operationStack.empty()&&(operationStack.peek()=='*'||operationStack.peek()=='÷')) {
                    operator();
                    if(BiggerFlag) return null;
                }
                if(temp!='=')
                    operationStack.push(temp);//将符号压入栈
                else{
                    while(!operationStack.empty()){
                        operator();
                        if(BiggerFlag) return null;
                    }
                }
            }
        }
        if (fractionStack.size() == 1 && bracketStack.empty()) {
            Fraction result = fractionStack.pop();
            return Fraction.GetFraction(result.molecule,result.demoninator);
        }
        return null;
    }

    public static int DealBracket(int point, String question) { //返回处理表达式的长度
        StringBuilder nowFractionNum1 = new StringBuilder();
        bracketStack.push('(');
        int aux = 0;
        for (int j = point + 1; j < question.length(); j++) {
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

                if (!operationStack.empty() && operationStack.peek() == '/') {
                    operationStack.pop();
                    int demoninator = NumStack.pop();
                    int molecule = NumStack.pop();
                    molecule += aux * demoninator;
                    fractionStack.push(new Fraction(molecule, demoninator));
                    aux = 0;
                }
                // if (!operationStack.empty() && operationStack.peek() != '/' && !NumStack.empty())
                if (!NumStack.empty()) {
                    int demoninator = 1;
                    int molecule = NumStack.pop();
                    fractionStack.push((new Fraction(molecule, demoninator)));
                }

                if (fractionStack.size() > 1 && temp == ')') {
                    operator();
                    if (BiggerFlag) return -1;
                }
                if (temp != ')') {
                    operationStack.push(temp);
                } else {
                    bracketStack.pop();
                    return j;
                }
            }
        }
        return 0;
    }

    private static boolean isNumber(char x) {
        return x >= '0' && x <= '9';
    }


    private static void operator() {

        Fraction y = fractionStack.pop();
        Fraction x = fractionStack.pop();
//        System.out.println("分子为"+x.molecule +" 分母为 "+x.demoninator);
//        System.out.println("分子为"+y.molecule +" 分母为 "+y.demoninator);
//        System.out.println("操作符为"+operationStack.peek());
        switch (operationStack.pop()) {
            case '+':
                Compute.add(x, y);
                break;
            case '-':
                Compute.sub(x, y);
                if (!isBigger()) BiggerFlag = true;
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
        nowFractionNum.delete(0, nowFractionNum.length());

    }


    private static boolean isBigger() {  //用来判断分数是否为负，加在减法运算之后
        Fraction x = fractionStack.peek();
        return x.demoninator * x.molecule >= 0;
    }

}


