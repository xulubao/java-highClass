package 多线程;

/**
 * @author XuLuBao
 * @version V1.0
 * @Package 多线程
 * @date 2020/9/1 14:44
 */
class ThreadDemo implements Runnable {
    public static void main(String[] args) {
       Thread t = new Thread(new ThreadDemo());
       t.start();
    }
    public void run(int limit){
        for (int i=0;i<limit;i++){
            System.out.println("valuse foi="+i);
        }
    }

    @Override
    public void run() {
        
    }
}
