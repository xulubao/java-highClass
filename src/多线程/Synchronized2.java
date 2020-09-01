package 多线程;

/**
 * 蛋糕，也就是多个线程都会访问的资源
 * @author LiZanhong
 *
 */
class Cake{
    /**
     * 同步资源：this
     * 这里是同步方法
     */
    public void execute() {
        for(int i = 0; i < 10; i ++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Boy implements Runnable{
    private Cake cake;

    public Boy(Cake cake) {
        super();
        this.cake = cake;
    }

    @Override
    public void run() {
        synchronized (cake) {
            cake.execute();
        }
    }
}

class Girl implements Runnable{
    private Cake cake;

    public Girl(Cake cake) {
        super();
        this.cake = cake;
    }

    @Override
    public void run() {
        synchronized (cake) {
            cake.execute();
        }
    }

}

/**
 * 线程同步
 * @author LiZanhong
 *
 */
public class Synchronized2 {
    public static void main(String[] args) {
        Cake cake = new Cake();//只有一个蛋糕
        new Thread(new Boy(cake)).start();
        new Thread(new Girl(cake)).start();
    }
}







