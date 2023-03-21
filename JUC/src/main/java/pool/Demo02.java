package pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 4大拒绝策略：
 * new ThreadPoolExecutor.AbortPolicy()    //银行满了，还有人进来，不处理这个人，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy()    // 哪来的回哪去，由调用线程处理该任务
 * new ThreadPoolExecutor.DiscardPolicy()    // 队列满了，丢掉任务，不会抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy()  // 队列满了，将最早进入队列的任务删除，之后再尝试加入队列
 */
public class Demo02 {
    public static void main(String[] args) {
        // 自定义线程池，ThreadPoolExecutor
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 队列满了，将最早进入队列的任务删除，之后再尝试加入队列

        try {
            // 最大承载 队列容量+maxPoolSize = 3+5
            // 超出，异常 RejectedExecutionException
            for (int i = 1; i <= 9; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" OK");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }

    }
}
