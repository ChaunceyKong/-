package map_;

import java.util.HashMap;
import java.util.Map;

public class Map_ {
    public static void main(String[] args) {
        //1. Map 中的 key 和 value 可以是任何引用类型的数据，会封装到HashMap$Node 对象中
        Map map = new HashMap();

        map.put("No1","111");
        map.put("No2","222");
        //2. Map 中的 key 不允许重复，原因和HashSet一样
        map.put("No1","333"); // 当有相同的key，会替换掉原有的 value
        //3. Map 中的 value 可以重复
        map.put("No4","111");
        //4. Map 的key可以为 null，value也可以为null，注意 key为null 有且只有一个，value为null可以有多个
        map.put(null,null);
        map.put(null,"444"); //替换 key为null的value
        map.put("No5",null);
        map.put("No5",null);
        //5. key 和 value 之间一一对应，通过一个key 总能找到对应的 value
        System.out.println(map.get("No4"));
    }
}
