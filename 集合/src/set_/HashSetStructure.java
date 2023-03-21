package set_;

public class HashSetStructure {
    public static void main(String[] args) {
        // 模拟一个HashSet（HashMap）的底层

        // 1.创建一个数组，数组的类型是Node
        // 2.有些人直接把 Node[] 称为表
        Node[] table = new Node[16];
        // 3.创建结点
        Node john = new Node("john",null);

        table[2] = john;
        Node jack = new Node("jack", null);
        john.next = jack; // 将 jack 结点挂载到 john
        Node rose = new Node("Rose", null);
        jack.next = rose;
        Node lucy = new Node("lucy", null);
        table[3] = lucy;
    }
}

class Node {
    Object item; // 存放数据
    Node next; // 指向下一个结点

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}
