
package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 7、一个静态同步方法，一个普通同步方法，一个对象，先发短信还是先打电话？ ans：先打电话，后发短信
 * 8、一个静态同步方法，一个普通同步方法，两个个对象，先发短信还是先打电话？ ans：先打电话，后发短信
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，两把锁，互不影响
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();


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

// Phone3唯一一个 class 对象
// Phone3.class
class Phone4 {
    // synchronized 锁的对象是方法的调用者 即phone
    // static 静态方法
    // 类一加载就有了！ 锁的是 Class 模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通同步方法
    public synchronized void call() {
        System.out.println("打电话");
    }


}
