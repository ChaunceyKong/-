package reflection;

import java.lang.annotation.ElementType;

//所有类型的Class
public class Test04 {
    public static void main(String[] args) {
        Class c1 = Object.class; //类的Class
        Class c2 = Comparable.class; //接口的Class
        Class c3 = String[].class; //一维数组的Class
        Class c4 = int[][].class; //二维数组的Class
        Class c5 = Override.class; //注解的Class
        Class c6 = ElementType.class; //枚举类型的Class
        Class c7 = Integer.class; //引用数据类型的Class
        Class c8 = void.class; //void的Class
        Class c9 = Class.class; //Class类的Class
        Class c10 = int.class; //基本数据类型的Class

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
        System.out.println(c10);

        //只要元素类型与维度一样，就是同一个Class
        int[] a = new int[10];
        int[] b = new int[100];
        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());
    }
}
