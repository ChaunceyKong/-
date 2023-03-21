package bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        test1();
    }
    /**
     * 抛出异常
     */
    public static void test1() {
        //参数为队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // 抛出异常 java.lang.IllegalStateException: Queue full
        // System.out.println(blockingQueue.add("d"));
        System.out.println("=========================");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // 抛出异常 java.util.NoSuchElementException
        // System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值，不抛出异常
     */
    public static void test2() {
        //参数为队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        // 不抛出异常 返回false
        // System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.element()); //查看队首元素是谁
        System.out.println("=============================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 不抛出异常  返回null
        // System.out.println(blockingQueue.poll());
    }

    /**
     * 等待，阻塞（一直阻塞）
     */
    public static void test3() throws InterruptedException {
        //参数为队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);


        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // 队列没有位置了，一直阻塞
        // blockingQueue.put("d");
        System.out.println("======================");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // 队列为空，一直等待，阻塞
        // System.out.println(blockingQueue.take());
    }

    /**
     * 等待，阻塞（等待超时）
     */
    public static void test4() throws InterruptedException {
        //参数为队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // 等待，超过2秒就返回false
        blockingQueue.offer("d",2, TimeUnit.SECONDS);
        System.out.println("====================");
        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        // 等待，超过2秒就返回null
        blockingQueue.poll(2,TimeUnit.SECONDS);
    }

}





















