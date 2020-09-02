package IO流;

import java.io.*;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package IO流
 * @date 2020/8/27 17:31
 */
public class Demo {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("F:\\中科韬睿\\JSP\\FILE/1.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            FileWriter writer = new FileWriter("F:/中科韬睿/JSP/FILE/2.txt");
            String ss = reader.readLine();
            while (ss!=null){
                ss = ss.replace("a","")
                        .replace("A","")
                        .replace("e","")
                        .replace("E","")
                        .replace("i","")
                        .replace("I","")
                        .replace("o","")
                        .replace("O","")
                        .replace("u","")
                        .replace("U","");
                writer.write(ss+"\r");
                ss = reader.readLine();
            }
            System.out.println("删除元音成功");
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
