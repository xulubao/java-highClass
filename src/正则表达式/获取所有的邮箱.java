package 正则表达式;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 正则表达式
 * @date 2020/8/28 21:26
 */
public class 获取所有的邮箱 {
    public static void main(String[] args) {
        String urls = "https://www.bsigroup.com/zh-CN/help/Email-contact-list/";
        String reg = "(?<=<a\\shref=\"mailto:)\\w+(\\w+|\\.\\w+)\\w+@\\w+(-\\w+|\\w+).com";
        String strs = "";
        try {
            URL url = new URL(urls);
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String str = reader.readLine();
            while (str!=null){
                strs+=str;
                str=reader.readLine();
            }
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(strs);
            PrintWriter writer = new PrintWriter("f:/中科韬睿/JSP/FILE/邮箱网址.txt");
            int i=0;
            while (matcher.find()){
                System.out.println(matcher.group()+"--------"+i++);
                writer.write(matcher.group()+"\r\n");
            }
            reader.close();
            writer.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
