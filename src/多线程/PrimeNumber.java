package 多线程;

/**
 * 多线程求解某范围素数,每个线程
 * @author XuLuBao
 * @version V1.0
 * @Package 多线程
 * @date 2020/9/1 17:31
 */
public class PrimeNumber {
    class numPrime extends Thread{
        private int numStart;
        private int numEnd;
        public numPrime(int numStart,int numEnd){
            this.numStart = numStart;
            this.numEnd = numEnd;
        }
        public void run() {
            //给循环命名A，用于子循环判断不是素数就下次循环
           A: for (int i=this.numStart;i<=this.numEnd;i++){
                for (int y=2;y<i/2;y++){
                    if (i%y==0){
                        // 如果能被整除则变量i肯定不是素数，跳出内层循环
                        continue A;
                    }
                }
                System.out.println(this.numStart+"至"+this.numEnd+"素数有："+i);//输出素数
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }

    public static void main(String[] args) {
        PrimeNumber num = new PrimeNumber();
        new Thread(num.new numPrime(1,1000)).start();
        new Thread(num.new numPrime(1001,2000)).start();
        new Thread(num.new numPrime(2001,3000)).start();
    }
}
