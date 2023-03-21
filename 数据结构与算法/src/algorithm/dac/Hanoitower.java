package algorithm.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

    //汉诺塔移动方法
    //使用分治算法解决
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第一个盘从 "+a+"->"+c);
        }
        else {
            //如果我们有n>=2个盘，我们总可以看做是两个盘，即最下面的一个盘 和 上面的所有盘
            //1.先把上面的所有盘 A->B
            hanoiTower(num - 1, a, c, b);
            //2.把最下面的一个盘 A->C
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            //3.把B塔的所有盘从 B->C
            hanoiTower(num-1, b, a, c);
        }
    }
}
