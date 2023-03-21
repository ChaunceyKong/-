package StaticProxy;

public class StaticProxy {
    public static void main(String[] args) {
        WeddingCompany weddingCompany=new WeddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}

interface Marry{
    void HappyMarry();
}

//真实角色，你去结婚
class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("我要结婚了！");
    }
}

//代理角色，帮助你结婚
class WeddingCompany implements Marry{
    private Marry target;

    public WeddingCompany(Marry target){
        this.target=target;
    }

    @Override
    public void HappyMarry() {
        brfore();
        this.target.HappyMarry(); //这就是真实对象
        after();
    }

    private void after() {
        System.out.println("结婚之后，收尾款");
    }

    private void brfore() {
        System.out.println("结婚之前，布置现场");
    }
}