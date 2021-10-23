import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Compare {

    /**
     * 求出自己写的答案用参考答案批改后得到的分数
     *
     * @param path1 自己写的答案
     * @param path2 标准答案
     */
    public static void CompareTxt(String path1, String path2) {
        //用来存放正确答案的下标
        List<Integer> rightIndex = new ArrayList<Integer>();
        //用来存放错误答案的下标
        List<Integer> errorIndex = new ArrayList<Integer>();
        File file1 = new File(path1);
        File file2 = new File(path2);
        String text1;
        String text2;
        int count = 0;
        if (!file1.isFile() || !file2.isFile()) System.out.println("文件格式不符合要求");
        if (!file1.exists() || !file2.exists()) System.out.println("文件不存在，无法进行比较");
        try {
            String encoding = "UTF-8";
            if ((file1.isFile() && file1.exists()) && (file2.isFile() && file2.exists())) { //判断读取的文件是否存在和是否为文件类型
                FileInputStream fileInputStream1 = new FileInputStream(file1);
                InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1, encoding);
                BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);

                FileInputStream fileInputStream2 = new FileInputStream(file2);
                InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2, encoding);
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);

                while (((text1 = bufferedReader1.readLine()) != null) && ((text2 = bufferedReader2.readLine()) != null)) {
                    if (text1.equals(text2)) {
                        rightIndex.add(count);
                    } else {
                        errorIndex.add(count);
                    }
                    count++;
                }
                while (bufferedReader1.readLine() != null) { //未回答出来的也算错误；
                    errorIndex.add(count);
                    count++;
                }
                WriteResult(rightIndex, true);
                WriteResult(errorIndex, false);

                //读取流关闭
                inputStreamReader1.close();
                bufferedReader1.close();
                fileInputStream1.close();

                inputStreamReader2.close();
                bufferedReader2.close();
                fileInputStream2.close();
            } else {
                System.out.println("文件不存在或者读取的不是文件");
            }
        } catch (IOException e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    /**
     * 将得分写入到txt中
     *
     * @param list 正确或错误的题目编号集合
     * @param flag 正确或者错误的标识
     */
    private static void WriteResult(List<Integer> list, boolean flag) {
        StringBuilder builder = new StringBuilder();
        if (flag) builder.append("Correct: ");
        else builder.append("Wrong: ");
        int length = list.size();
        builder.append(Integer.valueOf(length)).append("(");
        int temp;
        for (int i = 0; i < length; i++) {
            temp = list.get(i);
            if (i < length - 1)
                builder.append(Integer.valueOf(temp)).append(",");
            else
                builder.append(Integer.valueOf(temp));
        }
        builder.append(")");
        try {
            WriteUtil.write(Main.GradePath, builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
