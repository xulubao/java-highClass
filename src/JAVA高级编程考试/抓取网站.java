package JAVA高级编程考试;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package JAVA高级编程考试
 * @date 2020/9/2 8:57
 */
public class 抓取网站 {
    /**
     * 获取友情链接下的超链接
     * @return
     */
    public String findYq3(String url) throws IOException {
        //通过DIV的id判断是不是友情链接的块，截取下来
        String regx = "(?<=<div id=\"yq_3\" style=\"display:none\">)<a href=\"\\w+://\\w\\w.+(?=</div>)";
        URL urls = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(urls.openStream(),"utf-8"));
        StringBuffer buffer = new StringBuffer();
        String lin = reader.readLine();
        while (lin!=null){
            buffer.append(lin);
            lin = reader.readLine();
        }
        String str = buffer.toString();
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        return matcher.group();
    }

    /**
     * 通过获取到的友情链接的字符串获取到医院链接
     * @param str
     * @return
     */
    public Matcher findUrl(String str){
        String regx = "(?<=<a href=\")https?://\\w+(\\.\\w+)+";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    /**
     * 通过获取到的友情链接的字符串获取到医院名称
     * @param str
     * @return
     */
    public Matcher findName(String str){
        String regx="(?<=>)[\\u4e00-\\u9fa5]+(.{0,3})[\\u4e00-\\u9fa5]+";//由于获取到的字符串只包含汉字，所以此方法可以
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    /**
     * 写入到文件夹内
     * @param file 文件路径
     * @param matUrl url地址
     * @param matNmae 名称
     */
    public void writeFile(String file,Matcher matUrl,Matcher matNmae) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        while (matUrl.find()&&matNmae.find()){
            writer.write(matUrl.group()+"("+matNmae.group()+")\r\n");
        }
        writer.close();
    }
    public static void main(String[] args) {
        String url = "http://www.zznews.gov.cn/";
        抓取网站 file = new 抓取网站();
        try {
            String str =  file.findYq3(url);//获取友情链接
            Matcher matUrl = file.findUrl(str);
            Matcher matName = file.findName(str);
            file.writeFile("F:\\中科韬睿\\java高级\\java-highClass.\\file.txt",matUrl,matName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
