package unsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException 并发修改异常
public class ListTest {
    public static void main(String[] args) {
        // 并发下 ArrayList 不安全
        /**
         * 解决方案：
         * 1、Vector<>() 是安全的，换成List<String> list = new Vector<>();
         * 2、Collections包 List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、JUC包  List<String> list = new CopyOnWriteArrayList<>();
         */
        // CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
        // 读写分离
        List<String> list = new CopyOnWriteArrayList<>();


        for (int i = 1; i <= 10; i++) {

            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();

        }
    }
}
