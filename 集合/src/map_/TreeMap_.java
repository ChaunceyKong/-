package map_;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMap_ {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //下面调用String的compareTo方法进行字符串大小比较
                //return ((String) o1).compareTo(((String) o2));
                //按照字符串长度大小排序
                return ((String) o1).length() - ((String) o2).length();
            }
        });
        treeMap.put("jack","杰克");
        treeMap.put("tom","汤姆");
        treeMap.put("kristina","克瑞斯提诺");
        treeMap.put("smith","史密斯");
        System.out.println("TreeMap="+treeMap);

        /*
            1.构造器，把传入的实现了Comparable接口的匿名内部类(对象)，传给TreeMap的comparator
            public TreeMap(Comparator<? super K> comparator) {
                this.comparator = comparator;
            }
            2.调用put方法
            2.1 第一次添加，把k-v封装到Entry对象，放入root
            Entry<K,V> t = root;
            if (t == null) {
                compare(key, key); // type (and possibly null) check

                root = new Entry<>(key, value, null);
                size = 1;
                modCount++;
                return null;
            }
            2.2 以后添加
            Comparator<? super K> cpr = comparator;
            if (cpr != null) {
                do { //遍历所有的key，给当前key找到适当的位置
                    parent = t;
                    cmp = cpr.compare(key, t.key); //动态绑定到匿名内部类的compare()
                    if (cmp < 0)
                        t = t.left;
                    else if (cmp > 0)
                        t = t.right;
                    else //替换value
                        return t.setValue(value);
                } while (t != null);
            }
         */
    }
}
