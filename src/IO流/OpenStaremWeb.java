package IO流;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package PACKAGE_NAME
 * @date 2020/8/27 11:36
 */
public class OpenStaremWeb {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://haokan.faloo.com/29/29526/");
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is,"gb2312");
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("F:\\中科韬睿\\JSP\\FILE\\web.html")));
            while (line!=null){
                System.out.println(line);
                System.out.println();
                pw.write(line+"\r\n");
                line = reader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
