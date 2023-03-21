package tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(0,"Tom");
        HeroNode node1 = new HeroNode(1,"Jack");
        HeroNode node2 = new HeroNode(2,"Smith");
        HeroNode node3 = new HeroNode(3,"Mary");
        HeroNode node4 = new HeroNode(4,"King");

        //手动创建二叉树
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree=new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //以3号节点为例
        HeroNode leftNode=node3.getLeft();
        System.out.println("3号节点的前驱节点为 "+leftNode);
        HeroNode rightNode=node3.getRight();
        System.out.println("3号节点的后继节点为 "+rightNode);
    }
}

//定义ThreadedBinaryTree 实现线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载线索化方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }
    //编写对二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode node) {
        //如果node==null，不能线索化
        if (node==null) {
            return;
        }
        //(1)先线索化左子树
        threadedNodes(node.getLeft());
        //(2)线索化当前节点，有点难度
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight()==null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //(3)线索化右子树
        threadedNodes(node.getRight());
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right; //默认null
    //说明
    //1.如果leftType==0 表示指向的是左子树，如果1 则表示指向前驱节点
    //2.如果rightType==0 表示指向的是右子树，如果1 则表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no=no;
        this.name=name;
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no=no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HeroNode getLeft() {
        return left;
    }
    public void setLeft(HeroNode left) {
        this.left = left;
    }
    public HeroNode getRight() {
        return right;
    }
    public void setRight(HeroNode right) {
        this.right = right;
    }
    public int getLeftType() {
        return leftType;
    }
    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }
    public int getRightType() {
        return rightType;
    }
    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "tree.HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}