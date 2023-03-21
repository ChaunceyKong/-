package add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐7颗龙珠召唤神龙
         */
        //召唤神龙线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功");
        });

        for (int i = 1; i <= 7; i++) {
            //lambda表达式无法直接操作到i
            //匿名内部类、lambda表达式需要使用final（临时）变量，保证数据一致性
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集 "+temp+" 个龙珠");

                try {
                    cyclicBarrier.await(); //等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
