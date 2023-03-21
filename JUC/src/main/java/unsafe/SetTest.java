package unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同理可证：java.util.ConcurrentModificationException
 * 解决方案：
 * 1、Collections包   Set<String> set = Collections.synchronizedSet(new HashSet<>());
 * 2、JUC包   Set<String> set = new CopyOnWriteArraySet<>();
 */

public class SetTest {
    public static void main(String[] args) {
        // Set<String> set = new CopyOnWriteArraySet<>();
        Set<String> set = new HashSet<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
