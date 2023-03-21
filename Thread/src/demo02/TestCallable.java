package demo02;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

import org.apache.commons.io.FileUtils;


// 线程创建方式三：实现callable接口
/*
callable的好处：
1.可以定义返回值
2，可以抛出异常
 */
public class TestCallable implements Callable<Boolean> {
    private String url; //网络图片地址
    private String name; //保存的文件名

    public TestCallable(String url,String name) {
        this.url=url;
        this.name=name;
    }

    //下载图片线程的执行体
    @Override
    public Boolean call() throws Exception {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为："+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1=new TestCallable("https://blog.kuangstudy.com/user/themes/handsome/user/img/sj/1.jpg","1.jpg");
        TestCallable t2=new TestCallable("https://blog.kuangstudy.com/user/themes/handsome/user/img/sj/2.jpg","2.jpg");
        TestCallable t3=new TestCallable("https://blog.kuangstudy.com/user/themes/handsome/user/img/sj/3.jpg","3.jpg");

        //创建执行服务
        ExecutorService ser= Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> r1=ser.submit(t1);
        Future<Boolean> r2=ser.submit(t2);
        Future<Boolean> r3=ser.submit(t3);
        //获取结果
        boolean rs1=r1.get();
        boolean rs2=r2.get();
        boolean rs3=r3.get();
        //关闭服务
        ser.shutdownNow();
    }
}

// 下载器
class WebDownloader{

    //下载方法
    public void downloader(String url,String name) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}