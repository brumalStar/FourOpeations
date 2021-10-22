import java.util.Stack;

public class Calculator {  //计算表达式
   //保留情况 HashSet<String> QuestionSet=new HashSet<String>();

    public static Stack<Character> operationStack = null;//用来存放符号
    public static Stack<Integer> fractionStack = null;//用来存放数字

    public static String Calculate(String question){
        fractionStack = new Stack<Integer>();// 初始化栈
        operationStack = new Stack<Character>();
        //用来存放括号
        Stack<Character> bracketStack = new Stack<Character>();
        StringBuilder nowFractionNum = new StringBuilder();
        int length=question.length();
        for(int i=0;i<length;i++) {
            char temp = question.charAt(i);
            if (isNumber(temp)) nowFractionNum.append(temp);//如果是数字就放入到builder之中
            else if (isBracket(temp)) { //括号情况
                if (temp == '(') {
                    bracketStack.push('(');
                } else {
                    if (!bracketStack.empty()) { //如果有与右括号匹配的左括号
                        bracketStack.pop();
                        StringtoNum(nowFractionNum);//得出数字并且压入栈
                        operator();//进行计算操作
                    }else{
                        throw new RuntimeException("括号匹配不规范");//抛出异常
                    }
                }
            } else { //其他符号
                StringtoNum(nowFractionNum); //得出数字并且压入栈
                operationStack.push(temp);//将符号压入栈
                if(fractionStack.size()>1) {
                   operator();
                }
                if(temp!='=') operationStack.push(temp);
            }
        }
            if(fractionStack.size()==1&& bracketStack.empty()&&fractionStack.pop()>=0)
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
        switch (operationStack.pop()) {
            case '’':
            case '+':
            case '-':
            case '*':
            case '/':
            case '÷':
            default:
                break;
        }
    }

    private static void StringtoNum(StringBuilder nowFractionNum){
        String FractionNum = nowFractionNum.toString();
        int num = Integer.parseInt(FractionNum);
        fractionStack.push(num);
        nowFractionNum.delete(0,nowFractionNum.length());
    }

}
