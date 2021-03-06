import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteUtil {
//    这里path可以用final常量写死
    public static void write(String path, String result) {
        BufferedWriter out=null;
        try{
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true)));
            //写入结果并换行
            out.write(result);
            out.newLine();
        }catch (Exception e){
            System.out.println("写入失败");
            e.printStackTrace();
        }
        finally {
//            关闭流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
