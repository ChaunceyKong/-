package PC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    A执行完调用B，B执行完调用C，C执行完调用A
 */
public class Test03 {

    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();
    }
}

class Data3 {//资源类
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number = 1; // 1A 2B 3C

    public void printA() {
        lock.lock();
        //判断等待---执行业务---通知唤醒
        try {
            while (number != 1) { //判断等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"===>AAAAAA");
            //唤醒，指定唤醒的人，B
            number = 2;
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void printB() {
        lock.lock();

        try {
            while (number != 2) { //判断等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"===>BBBBBB");
            //唤醒，指定的人，C
            number = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>CCCCCC");
            //唤醒，指定的人，A
            number = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}





















