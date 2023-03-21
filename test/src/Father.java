public class Father {
    static {
        System.out.println("父类中的静态代码块");
    }

    Father() {
        System.out.println("父类中的构造函数");
    }
    {
        System.out.println("父类中的非静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("父类中的main方法");
        new Father();
    }
}
