package map_;

import java.util.*;

public class MapExercise {
    public static void main(String[] args) {
        Map map = new HashMap();

        map.put(1,new Person("张三",10000,1));
        map.put(2,new Person("李四",20000,2));
        map.put(3,new Person("王五",30000,3));

        //遍历
        System.out.println("--------方式一（entrySet）-------");
        Set entrySet = map.entrySet();
        // 迭代器
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Person person = (Person) entry.getValue();
            if (person.getSalary() > 18000) {
                System.out.println(person);
            }
        }

        System.out.println("-------方式二（values）-------");
        Collection values = map.values();
        for (Object o : values) {
            Person person = (Person) o;
            if (person.getSalary() > 18000) {
                System.out.println(person);
            }
        }





    }
}

class Person {
    private String name;
    private double salary;
    private int id;

    public Person(String name, double salary, int id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }
}
