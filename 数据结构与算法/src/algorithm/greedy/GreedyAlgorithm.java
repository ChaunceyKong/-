package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到Map
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        //allAreas 存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        //迭代生成
        //for (HashSet<String> value: broadcasts.values()) {
        //    allAreas.addAll(value);
        //}
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList，存放选择的电台
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合，在遍历过程中，存放电台覆盖集合与当前剩余地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义一个maxKey，保存在一次遍历过程中，能够覆盖最多地区对应的电台的key
        //如果maxKey不为空，则会加入到selects
        String maxKey = null;
        //定义一个集合，表示最大电台覆盖区域
        HashSet<String> maxKeyAreas = new HashSet<String>();
        while (allAreas.size() != 0) { //如果allAreas不为0，则表示还没有覆盖到所有地区
            //每进行一个while，需要maxKey置空
            maxKey = null;
            //遍历 broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                //没进行一次for
                tempSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);

                //定义一个集合，表示最大电台覆盖区域
                if (maxKey != null) {
                    maxKeyAreas = broadcasts.get(maxKey);
                    maxKeyAreas.retainAll(allAreas);
                }
                //如果当前电台覆盖的区域 多于 maxKey对应电台覆盖的区域 则maxKey=key;
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > maxKeyAreas.size())) {
                    maxKey=key;
                }
            }
            //maxKey != null，就应该加入selects集合
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的电台覆盖区域从allAreas集合中删除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果为："+selects);

    }
}