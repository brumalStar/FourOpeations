import java.io.*;

public class ReadUtil {
    public static String read(String path) throws IOException {

        //将读出的字符串用StringBuilder连接。
        StringBuilder builder = new StringBuilder();
        String text=null;
        File afile=new File(path);
        //判断其是否为文件并且是否存在
        try{
            String encoding="UTF-8";
            if(afile.isFile() && afile.exists()) { //判断读取的文件是否存在和是否为文件类型
                FileInputStream fileInputStream = new FileInputStream(afile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while ((text = bufferedReader.readLine()) != null) {
                    builder.append(text);
                }
                //读取流关闭
                inputStreamReader.close();
                bufferedReader.close();
                fileInputStream.close();
            }
            else{
                System.out.println("文件不存在或者读取的不是文件");
            }
        }catch(IOException e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        //返回字符串类型
        return builder.toString();
    }
}
