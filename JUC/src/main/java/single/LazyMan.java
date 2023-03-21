package single;

/**
 * 懒汉式单例模式
 * 单线程下OK，多线程使用双重检测锁
 */
public class LazyMan {
    private LazyMan() {
        System.out.println(Thread.currentThread().getName()+" OK");
    }

    private volatile static LazyMan lazyMan;

    // 双重检测锁模式 double check lock （DCL）
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个内存空间
                     *
                     * 123   线程A
                     * 132   线程B  出现了执行重排，需要声明volatile禁止指令重排
                     */
                }
            }
        }

        return lazyMan;
    }

    // 多线程并发
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                lazyMan.getInstance();
            }).start();
        }
    }
}
