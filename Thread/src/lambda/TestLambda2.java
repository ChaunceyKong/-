package lambda;

public class TestLambda2 {
    //2.静态内部类
    static class Love2 implements ILove{

        @Override
        public void love(int a) {
            System.out.println("i love you--->"+a);
        }
    }

    public static void main(String[] args) {
        ILove love=new Love();
        love.love(1);

        love=new Love2();
        love.love(2);

        //3.局部类
        class Love3 implements ILove{

            @Override
            public void love(int a) {
                System.out.println("i love you--->"+a);
            }
        }
        love=new Love3();
        love.love(3);

        //4.匿名内部类
        love = new ILove() {
            @Override
            public void love(int a) {
                System.out.println("i love you--->"+a);
            }
        };
        love.love(4);

        //5.lambda表达式
        love=(int a)->{
            System.out.println("i love you--->"+a);
        };
        love.love(5);

        //6.简化：去掉参数类型
        love=(a)->{
            System.out.println("i love you--->"+a);
        };
        love.love(6);

        //7.简化：去掉括号
        love=a->System.out.println("i love you--->"+a);
        //总结：
        //1.lambda表达式只能有一行代码的情况下才能简化成为一行，如果有多行就用代码块包裹；
        //2.前提是接口为函数式接口
        //3.多个参数也可以去掉参数类型，要去掉都去掉，但是必须加上括号
        love.love(7);

    }
}

//接口
interface ILove{
    public abstract void love(int a);
}

//1.外部实现类
class Love implements ILove{

    @Override
    public void love(int a) {
        System.out.println("i love you--->"+a);
    }
}