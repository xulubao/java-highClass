package JAVA高级编程考试;

import java.util.Date;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package JAVA高级编程考试
 * @date 2020/9/2 10:01
 */
public class ThreadDemo {
    public static void main(String[] args) {
        new Thread(new FindDate()).start();
        new Thread(new Sum(1,10000)).start();
    }

}
/**
 * 1秒的频率显示当前系统时间
 */
class FindDate implements Runnable{
    @Override
    public void run() {
        Date dates = new Date();
        while (true){
            long date = System.currentTimeMillis();
            dates.setTime(date);
            System.out.println(dates.toLocaleString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 计算1+2+3+...10000
 */
class Sum implements Runnable{
    private int start;
    private int end;
    public Sum(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
        long sum = 0;
        for (int i=this.start;i<=this.end;i++){
            sum +=i;
            System.out.println(sum);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
