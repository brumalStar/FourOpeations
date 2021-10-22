import java.util.Stack;

public class Calculator {  //计算表达式
   //保留情况 HashSet<String> QuestionSet=new HashSet<String>();

    public static Stack<Character> operationStack = null;//用来存放符号
    public static Stack<Fraction> fractionStack = null;//用来存放分数
    public static Stack<Integer> NumStack=null;


    public static String Calculate(String question){
        fractionStack = new Stack<Fraction>();//
        operationStack = new Stack<Character>();
        NumStack=new Stack<Integer>();
        //用来存放括号
        Stack<Character> bracketStack = new Stack<Character>();
        StringBuilder nowFractionNum = new StringBuilder();
        int length=question.length();
        int aux=0;
        for(int i=0;i<length;i++) {
            char temp = question.charAt(i);
            if(temp=='=') break;
            if (isNumber(temp)) nowFractionNum.append(temp);//如果是数字就放入到builder之中
            else if (isBracket(temp)) { //括号情况
                if (temp == '(') {
                    bracketStack.push('(');
                } else {
                    if (!bracketStack.empty()&&bracketStack.pop()=='(') { //如果有与右括号匹配的左括号
                        StringToNum(nowFractionNum);//得出数字并且压入栈
                        operator();//进行计算操作
                    }else{
                        throw new RuntimeException("括号匹配不规范");//抛出异常
                    }
                }
            }
            else if(temp=='’'){
                StringToNum(nowFractionNum);
                aux=NumStack.pop(); //得出真分数的真值；
            }
            else { //其他符号
                StringToNum(nowFractionNum); //得出数字并且压入栈
                if(operationStack.pop()=='/') {
                    int demoninator=NumStack.pop();
                    int molecule=NumStack.pop();
                    molecule+=aux*demoninator;
                    fractionStack.push(new Fraction(molecule,demoninator));
                    aux=0;
                }
                operationStack.push(temp);//将符号压入栈
                if(fractionStack.size()>1&&temp!='/') {
                   operator();
                }
            }
        }
            if(fractionStack.size()==1&& bracketStack.empty())
            return String.valueOf(fractionStack.pop());
            else return null;
        }



    private static boolean isNumber(char x){
        return x>='0'&&x<='9';
    }

    private static boolean isBracket(char x){
      return x=='('||x==')';
    }

    private static void operator(){
        Fraction x = fractionStack.pop();
        Fraction y = fractionStack.pop();
        switch (operationStack.pop()) {
            case '+':Compute.add(x,y);break;
            case '-':Compute.sub(x,y);break;
            case '*':Compute.mul(x,y);break;
            case '÷':Compute.div(x,y);break;
            default:
                break;
        }
    }

    private static void StringToNum(StringBuilder nowFractionNum){ //将缓存的字符串转为数字
        String checkFractionNum = nowFractionNum.toString(); // 将数字缓存转为字符串
        int num = Integer.parseInt(checkFractionNum); // 将数字字符串转为长整型数
        NumStack.push(num);
        nowFractionNum.delete(0,nowFractionNum.length());
        }
    }




