package IO流;

import java.io.*;
import java.net.URL;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package IO流
 * @date 2020/8/27 8:52
 */
public class 复制图片 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            URL url = new URL("http://img.netbian.com/file/2018/1218/5174f6bd57412ee0d66bcff52629cf5e.jpg");
            File file =  new File("F:/中科韬睿/JSP/FILE/img.jpg");
            InputStream fis = url.openStream();
            File file1 = new File("F:/中科韬睿/JSP/FILE/复制.jpg");
            FileOutputStream fos = new FileOutputStream(file1);
            /*int b = fis.read();
            while (b!=-1){
                fos.write(b);
                b = fis.read();
            }*/
            byte[] b = new byte[1024];
            int len = fis.read(b);
            while (len>0){
                fos.write(b,0,len);
                len = fis.read(b);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"毫秒");
    }
}
