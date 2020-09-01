package 多线程;

/**
 * 银行ATM机取款及柜台取款同步
 * @author XuLuBao
 * @version V1.0
 * @Package 多线程
 * @date 2020/9/1 17:51
 */
public class ATMAccess {


    public static void main(String[] args) {
        Money m = new Money();
        new Thread(new AtmMoney(m,-60)).start();
        new Thread(new CounterMoney(m,90)).start();
        new Thread(new AtmMoney(m,-60)).start();
        new Thread(new CounterMoney(m,-90)).start();
    }

}
/**
 * 余额
 */
class Money{
    double money=1000;//这里默认设置存款为1000元，用于后续操作
    /**
     * 通过不同的方式取款，同步数据
     * @param moneyExe 存取款数据
     */
    public synchronized void moneyExe(double moneyExe){
        if(money-moneyExe<0){
            System.out.println("余额不足！");
            return;
        }
        this.money = this.money + moneyExe;
        System.out.println("当前余额"+this.money);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 通过ATM对余额进行操作
 */
class AtmMoney implements Runnable{
    private Money M;
    private double money;//要执行的金额
    public AtmMoney(Money M,double moneyExe){
        this.M = M;
        this.money = moneyExe;
    }
    @Override
    public void run() {
        System.out.println("通过ATM"+(money<0?"取钱":"存钱")+money);
        M.moneyExe(this.money);
    }
}

/**
 * 通过人工柜台对金额进行操作
 */
class CounterMoney implements Runnable{
    private Money M;
    private double money;//要执行的金额
    public CounterMoney(Money M,double moneyExe){
        this.M = M;
        this.money = moneyExe;
    }
    @Override
    public void run() {
        System.out.println("通过人工柜台"+(money<0?"取钱":"存钱")+money);
        M.moneyExe(this.money);

    }
}