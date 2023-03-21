package set_;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSet_ {
    public static void main(String[] args) {
        //1. 使用TreeSet提供的一个构造器，可以传入一个比较器（匿名内部类）
        // 并指定顺序规则
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //下面调用String的compareTo方法进行字符串大小比较
                //return ((String) o1).compareTo(((String) o2));
                //按照字符串长度大小排序
                return ((String) o1).length() - ((String) o2).length();
            }
        });
        treeSet.add("jack");
        treeSet.add("tom");
        treeSet.add("sp");
        treeSet.add("a");

        System.out.println("treeSet = " + treeSet);

        /*
            1. 构造器把传入的比较器对象，赋给了 TreeSet底层TreeMap的一个属性this.comparator
            public TreeMap(Comparator<? super K> comparator) {
                this.comparator = comparator;
            }
            2.在调用 treeSet.add("tom")时，在底层会执行到
            if (cpr != null) { //cpr 就是我们的匿名内部类（对象）
                do {
                    parent = t;
                    cmp = cpr.compare(key, t.key); //动态绑定到匿名内部类的compare()方法
                    if (cmp < 0)
                        t = t.left;
                    else if (cmp > 0)
                        t = t.right;
                    else //如果相等，即返回0，这个key加入不了
                        return t.setValue(value);
                } while (t != null);
            }


         */
    }
}
