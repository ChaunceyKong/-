import java.sql.SQLOutput;

public class Son extends Father {
    static {
        System.out.println("子类中的静态代码块");
    }

    Son() {
        System.out.println("子类中的构造方法");
    }
    {
        System.out.println("子类中的非静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("子类中的main方法");
        new Son();
    }
}
