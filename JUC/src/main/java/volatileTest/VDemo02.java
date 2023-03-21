package volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 不保证原子性
 */
public class VDemo02 {

    //原子类Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        // num++; // 不是一个原子性操作
        num.getAndIncrement(); // AtomicInteger 的 +1 方法，CAS
    }

    public static void main(String[] args) {
        //理论上 num的结果为 20000
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { //main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
}
