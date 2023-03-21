
package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5、增加两个静态同步方法，只有一个对象，先打印发短信还是hello？ ans：先发短信，后打电话
 * 6、两个对象，两个静态同步方法，先发短信还是先打电话？ ans：先发短信，后打电话
 */
public class Test3 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，两把锁，互不影响
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();


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
class Phone3 {
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

    public static synchronized void call() {
        System.out.println("打电话");
    }

}
