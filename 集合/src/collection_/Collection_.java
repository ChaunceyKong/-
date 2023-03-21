package collection_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Collection_ {
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("jack");
        list.add("tom");
        list.add("mary");

        // 使用增强for循环
        for (Object o : list) {
            System.out.println(o);
        }


    }
}
