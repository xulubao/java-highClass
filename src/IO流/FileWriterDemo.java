package IO流;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package IO流
 * @date 2020/8/27 10:18
 */
public class FileWriterDemo {
    public static void main(String[] args) {
        try {
            FileWriter  writer = new FileWriter("f:/中科韬睿/JSP/FILE/x.txt",true);
            writer.write("北京欢迎你");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
