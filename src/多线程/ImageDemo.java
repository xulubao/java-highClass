package 多线程;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 多线程
 * @date 2020/9/1 13:15
 */

class Download implements Runnable{
    private String imgUrl;//要下载的图片
    private File saveDir; //保存的目录

    public Download(String imgUrl, File saveDir) {
        super();
        this.imgUrl = imgUrl;
        this.saveDir = saveDir;
    }

    /**
     * 获取网址中的图片名
     * @param imgUrl
     * @return
     */
    public String getFileName(String imgUrl) {
        int pos = imgUrl.lastIndexOf('/');//找到最一个”/“的位置
        return imgUrl.substring(pos + 1);//取子串
    }

    /**
     * 处理下载任务
     */
    @Override
    public void run() {
        try {
            URL url = new URL(imgUrl);
            InputStream is = url.openStream();
            FileOutputStream fos = new FileOutputStream(
                    new File(saveDir, getFileName(imgUrl)));
            byte[] b = new byte[2 * 1024];
            int len = is.read(b);//读取当前批次的字节
            while(len > 0) {
                fos.write(b, 0, len); //保存到输出流中
                len = is.read(b); //继续读取下一批字节
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fos.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ImageDemo {
    /**
     * 从网页中读取HTML源码
     * @param page
     * @return
     */
    public String readHtmlSource(String page) {
        try {
            URL url = new URL(page);
            URLConnection conn = url.openConnection();//获取连接
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3775.400 QQBrowser/10.6.4208.400");
            InputStream is = conn.getInputStream();
            //创建流
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(is,
                            "gbk"));
            //从流中读取字符串
            StringBuffer lines = new StringBuffer();
            String line = br.readLine();
            while(line != null) {
                //lines += line;//最low的做法
                lines.append(line);//连接字符串
                line = br.readLine();
            }

            String str = lines.toString();//转换成String
            br.close();
            return str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从HTML源码中解析出图片地址
     * @param src
     * @return
     */
    public List<String> getImageUrl(String src) {
        List<String> list = new ArrayList<String>();
        //通过正则表达式
        String regex = "(?<=img src=\").+?(?=\")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(src);
        while(m.find()) {
            String img = m.group();
            list.add(img);
        }
        return list;
    }



    /**
     * 下载图片
     * @param imgUrl 图片地址
     * @param saveDir 图片的保存路径
     */
    public void download() {


    }

    /**
     * 下载所有的图片
     * @param imgUrls
     * @param saveDir
     */
    public void downloadAll(List<String> imgUrls, File saveDir) {
        if(imgUrls == null) return;
        for(String imgUrl: imgUrls) {
            //this.download(imgUrl, saveDir);
            new Thread(new Download(imgUrl, saveDir)).start();
        }
    }

    public static void main(String[] args) {
        ImageDemo image = new ImageDemo();
        String page = "http://www.netbian.com/";
        //获取网页的HTML源码
        String htmlSrc =  image.readHtmlSource(page);
        //获取HTML源码中的图片地址
        List<String> list = image.getImageUrl(htmlSrc);
        //下载
        image.downloadAll(list, new File("g:/imgs"));

        System.out.println("下载完成");
    }
}
