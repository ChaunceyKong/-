package set_;

import java.util.HashSet;
import java.util.Set;

public class HashSet_ {
    public static void main(String[] args) {
        Set set = new HashSet();

        System.out.println(set.add("john")); //T
        System.out.println(set.add("lucy")); //T
        System.out.println(set.add("john")); //F
        System.out.println(set.add("jack")); //T
        System.out.println(set.add("rose")); //T

        set.remove("john");
        System.out.println("set=" + set); //3个

        // 如何理解 HashSet 不能添加相同的元素
        set = new HashSet();
        set.add("lucy"); //T
        set.add("lucy"); //F
        set.add(new Dog("tom")); //T
        set.add(new Dog("tom")); //T
        System.out.println("set=" + set);

        //再加深一下
        set.add(new String("kkk")); //T
        set.add(new String("kkk")); //F
    }
}

class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
