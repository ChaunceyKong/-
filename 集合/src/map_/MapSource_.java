package map_;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapSource_ {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("No1","111");
        map.put("No2","222");

        //1. k-v 最后是 HashMap$Node node = newNode(hash,key,value,null)
        //2. k-v 为了方便程序员的遍历，还会创建 EntrySet 集合，该集合存放的元素的类型是 Entry，
        //   而一个Entry 对象就有key 和 value，即 EntrySet<Entry<key,value>>
        //3. entrySet 中，定义的类型是 Map.Entry，但实际上存放的还是 HashMap$Node
        //   这时因为 HashMap$Node implements Map.Entry   接口多态
        //4. 当把 HashMap$Node 对象存放到 entrySet就方便我们的遍历，因为 Map.Entry 提供了重要方法
        //   getKey()   getValue()

        Set set = map.entrySet();
        System.out.println(set.getClass());
        for (Object obj : set) {
            //System.out.println(obj.getClass()); //HashMap$Node
            //为了从 HashMap$Node 取出k-v
            //1. 先做一个向下转型
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        Set set1 = map.keySet();
        System.out.println(set1.getClass());
        Collection values = map.values();
        System.out.println(values.getClass());
    }
}
