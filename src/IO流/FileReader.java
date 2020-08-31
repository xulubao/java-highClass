package IO流;

import java.io.*;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package IO流
 * @date 2020/8/27 11:15
 */
public class FileReader {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("f:/中科韬睿/JSP/FILE/x.txt");
            InputStreamReader isr = new InputStreamReader(fis,"utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (line!=null){
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
