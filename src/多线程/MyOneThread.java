package 多线程;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 多线程
 * @date 2020/9/1 10:34
 */

/**
 * 减法线程
 */
class sub implements Runnable{
    @Override
    public void run() {
        int a = 10;
        int b = 12;
        int c = a-b;
        System.out.println(c);
    }
}
class add implements Runnable{
    @Override
    public void run() {
        int a = 10;
        int b = 12;
        int sum = a+b;
        System.out.println(sum);
    }
}
public class MyOneThread {
    public static void main(String[] args) {
        //创建多线程对象
        Thread thread1 = new Thread(new sub());
        Thread thread2 = new Thread(new add());
        //启动线程
        thread1.start();
        thread2.start();
    }
}
