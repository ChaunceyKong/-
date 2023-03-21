package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用 CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回结果的异步回调 runAsync
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
//        });
//        System.out.println("111111");
//        completableFuture.get(); //获取执行结果

        // 有返回结果的异步回调
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
            return 1024;
        });

        completableFuture.whenComplete((t,u)->{
            System.out.println("t=>"+t); //正常的返回结果
            System.out.println("u=>"+u); //错误信息
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return 233; //可以获取到错误的返回结果
        }).get();
    }
}


























