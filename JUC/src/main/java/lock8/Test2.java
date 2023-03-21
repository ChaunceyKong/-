
package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 3、增加了一个普通方法hello()，先打印发短信还是hello？ ans：先打印hello，再发短信
 * 4、两个对象，两个同步方法，先打印发短信还是打电话？ ans：先打电话，后发短信
 */
public class Test2 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，两把锁，互不影响
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(()->{
            phone1.sendSms();
        },"A").start();

        //休息1s
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

class Phone2 {
    // synchronized 锁的对象是方法的调用者 即phone
    // 两个方法用的是同一把锁，谁先拿到谁执行！
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    //这里没有锁，不是同步方法，不受锁的影响
    public void hello() {
        System.out.println("Hello");
    }
}
