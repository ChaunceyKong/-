package syn;

//安全的取钱
//两个人去银行取钱
public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100,"结婚基金");

        Drawing you = new Drawing(account,50, "you");
        Drawing girlFriend = new Drawing(account,100, "girlFriend");

        you.start();
        girlFriend.start();
    }
}

//账户
class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread{

    //账户
    Account account;
    //取了多少钱
    int drawMoney;
    //现在手里有多少钱
    int nowMoney;

    public Drawing(Account account,int drawMoney,String name) {
        super(name);
        this.account = account;
        this.drawMoney = drawMoney;
    }

    //取钱
    //synchronized默认锁的是this
    @Override
    public void run() {

        //锁的对象就是变化的量，需要增删改的对象
        synchronized (account) {

            //判断有没有钱
            if (account.money-drawMoney < 0) {
                System.out.println(Thread.currentThread().getName()+"钱不够了，取不了");
                return;
            }
            //sleep可以放大问题的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额 = 余额 - 你取的钱
            account.money = account.money - drawMoney;
            //你手里的钱
            nowMoney = nowMoney + drawMoney;

            System.out.println(account.name+"余额为："+account.money);
            //Thread.currentThread().getName()=this.getName()
            System.out.println(this.getName()+"手里的钱："+nowMoney);

        }

    }
}