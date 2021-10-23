public class TestCompare {
    public static void main(String[] args) {
        String path1="C:/Users/mz/Desktop/partner6.0/src/main/resources/answer.txt";
        String path2="C:/Users/mz/Desktop/partner6.0/src/main/resources/answers.txt";

        Compare.CompareTxt(path1,path2);
        System.out.println("文件比较完成");

    }
}
