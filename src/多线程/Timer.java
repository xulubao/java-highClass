package 多线程;

import javax.swing.*;
import java.util.Date;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 多线程
 * @date 2020/9/1 13:17
 */
public class Timer extends JFrame {
    class Clock implements Runnable{
        @Override
        public void run() {
            Date date = new Date();
            while (true){
                Long m = System.currentTimeMillis();
                date.setTime(m);
                Timer.this.setTitle(date.toLocaleString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public Timer(){
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    public static void main(String[] args) {
        Timer timer = new Timer();
        new Thread(timer.new Clock()).start();
    }
}
