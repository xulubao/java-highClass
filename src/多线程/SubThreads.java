package 多线程;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.Map;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 多线程
 * @date 2020/9/1 16:07
 */
public class SubThreads extends JFrame {
    JTextArea jt2 = new JTextArea();//一个文本区
    class SubThread implements Runnable{
        private String threadName;
        public SubThread(String threadName){
            this.threadName = threadName;
        }
        @Override
        public void run() {
            for (int i=1;i<=5;i++){
                //每一次循环添加信息到文本区显示
                jt2.setText(jt2.getText()+threadName+"循环了"+i+"次！"+"\n");
                try {
                    Thread.sleep((long) (Math.random()*(1000-1)+1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            jt2.setText(jt2.getText()+threadName+"finished\n");
        }
    }
    public SubThreads(){
        jt2.setBackground(Color.white);//文本区的颜色
        JScrollPane jsp = new JScrollPane(jt2);//滚动条
        this.setSize(600,900);//设置程序宽度
        this.setTitle("我的第一个Application程序");//设置标题
        this.setVisible(true);//窗体是否可见
        this.add(BorderLayout.NORTH,jt2);//将文本区添加到程序的顶部位置
    }


    public static void main(String[] args) {
        SubThreads T1 = new SubThreads();
        new Thread(T1.new SubThread("Frist")).start();
        new Thread(T1.new SubThread("Second")).start();
        new Thread(T1.new SubThread("Third")).start();
    }
}
