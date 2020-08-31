package 正则表达式;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 正则表达式
 * @date 2020/8/28 18:52
 */
public class 下载所有的图片网址 {
    public static void main(String[] args) throws IOException {
        System.out.print("请输入要网址：");
        //String urls = "http://www.netbian.com/";
        String urls = new Scanner(System.in).nextLine();
        //(?<=<img\ssrc=")https?://(\w)+(\.\w+)+(\/\w+)+\.\w+
        String reg = "https?://(\\w)+(\\.\\w+)+(\\/\\w+)+.jpg";
        String filePate = "F:\\中科韬睿\\JSP\\FILE\\";
        String imgSrc = "";
        BufferedReader reader = null;
        InputStream is = null;
        try {
            URL url = new URL(urls);
            is = url.openStream();
            reader = new BufferedReader(new InputStreamReader(is,"gbk"));
            String str = reader.readLine();
            while (str!=null){
                imgSrc+=reader.readLine();
                str = reader.readLine();
            }
            System.out.println(imgSrc);
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(imgSrc);
            while (matcher.find()){
                System.out.println("图片路径："+matcher.group());
                URL url1 = new URL(matcher.group());
                InputStream fis = url1.openStream();
                String reg2 = "\\w+(?=\\.jpg)";
                Pattern pattern1 = Pattern.compile(reg2);
                Matcher matcher1 = pattern1.matcher(matcher.group());
                matcher1.find();
                FileOutputStream fos = new FileOutputStream(filePate+"\\"+matcher1.group()+".jpg");
                byte[] bytes = new byte[1024];
                int len = fis.read(bytes);
                while (len!=-1){
                    fos.write(bytes,0,len);
                    len = fis.read(bytes);
                }
                fis.close();
                fos.close();
            }
            System.out.println("下载图片成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}
