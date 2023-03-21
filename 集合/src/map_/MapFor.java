package map_;

import java.util.*;

public class MapFor {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("No1","111");
        map.put("No2","222");
        map.put("No3","333");
        map.put("No4","444");
        map.put("No5","555");

        //第一种：先取出所有key，通过key 取出对应value
        Set keySet = map.keySet();
        //(1) 增强for
        System.out.println("------方式一------");
        for (Object key : keySet) {
            System.out.println(key + "-" + map.get(key));
        }
        //(2) 迭代器
        System.out.println("------方式二------");
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key + "-" + map.get(key));
        }

        //第二种：直接取出value
        Collection values = map.values();
        //(1) 增强for
        for (Object value : values) {
            System.out.println(value);
        }
        //(2) 迭代器
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            Object value = iterator1.next();
            System.out.println(value);
        }

        //第三种：通过EntrySet来获取
        Set entrySet = map.entrySet();
        //(1) 增强for
        for (Object entry : entrySet) {
            //将entry 转成 Map.Entry
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey()+"-"+m.getValue());
        }
        //(2) 迭代器
        Iterator iterator2 = entrySet.iterator();
        while (iterator2.hasNext()) {
            Object entry = iterator2.next();
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey()+"-"+m.getValue());
        }
    }
}
