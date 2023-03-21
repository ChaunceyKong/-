package set_;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetSource {
    public static void main(String[] args) {
        // 分析一下 LinkedHashSet 底层机制
        Set set = new LinkedHashSet();
        set.add(new String("AA"));
        set.add(456);
        set.add(456);
        set.add(new Customer("刘",1001));
        set.add(123);
        set.add("KXY");

        System.out.println("linkedHashSet=" + set);

        // 解读
        //1. LinkedHashSet 元素加入顺序和取出顺序一致
        //2. LinkedHashSet 底层维护的是一个LinkedHashMap（是HashMap的子类）
        //3. LinkedHashSet 底层结构（数组+双向链表）
        //4. 第一次添加时，直接将 数组table 扩容到16，存放的结点类型是 LinkedHashMap$Entry
        //5. 数组是 HashMap$Node[] 存放的元素是 LinkedHashMap$Entry类型  数组多态：数组存放子类型元素
        /*  Entry 继承了 Node
            static class Entry<K,V> extends HashMap.Node<K,V> {
                Entry<K,V> before, after;
                Entry(int hash, K key, V value, Node<K,V> next) {
                    super(hash, key, value, next);
                }
            }
         */
    }
}

class Customer {
    private String name;
    private int no;

    public Customer(String name, int no) {
        this.name = name;
        this.no = no;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }
}
