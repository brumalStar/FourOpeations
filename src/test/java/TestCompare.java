import org.junit.Test;

public class TestCompare {

    @Test
    public void testCompare(){
        String path1="MyAnswer.txt";
        String path2="Answers.txt";
        Compare.CompareTxt(path1,path2);
        System.out.println("文件比较完成");
    }
}
