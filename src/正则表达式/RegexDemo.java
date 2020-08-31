package 正则表达式;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 正则表达式
 * @date 2020/8/28 10:36
 * Git操作了一句话
 */
public class RegexDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("F:/中科韬睿/JSP/file/W.txt");
        URL url = new URL("https://www.bsigroup.com/zh-CN/help/Email-contact-list/");
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(1000*10);
        BufferedReader bufr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        Writer wt = new FileWriter(file,true);
        PrintWriter pw = new PrintWriter(wt,true);

        String line = null;
        String regex = "[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?";

        while((line = bufr.readLine())!=null)
        {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            while(m.find()) {
                pw.write(m.group());
                System.out.println(m.group());
            }
        }
        pw.flush();
        pw.close();
        bufr.close();
    }
}
