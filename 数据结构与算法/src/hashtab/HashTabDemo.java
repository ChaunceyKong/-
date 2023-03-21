package hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args){
        //创建哈希表
        HashTab hashTab=new HashTab(7);
        //写一个简单的菜单
        String key="";
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("add:添加雇员");
            System.out.println("find:查找雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退出");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id=scanner.nextInt();
                    System.out.println("输入名字");
                    String name=scanner.next();
                    //创建雇员
                    Emp emp=new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "find":
                    System.out.println("请输入查找雇员id");
                    id=scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//创建HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示多少条链表

    //构造器
    public HashTab(int size){
        this.size=size;
        //初始化empLinkedListArray
        empLinkedListArray=new EmpLinkedList[size];
        //留一个坑，这时不要忘了分别初始化每个链表
        for (int i=0;i<size;i++){
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkListNo=hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkListNo].add(emp);
    }

    //遍历所有的链表,遍历HashTable
    public void list(){
        for (int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }
    //编写散列函数，使用简单的取模法
    public int hashFun(int id){
        return id % size;
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkListNo=hashFun(id);
        Emp emp=empLinkedListArray[empLinkListNo].findEmpById(id);
        if (emp!=null){ //找到了
            System.out.printf("在第 %d 条链表中，找到雇员id = %d\n",empLinkListNo,id);
        }
        else {
            System.out.println("在哈希表中，没有找到该雇员\n");
        }
    }

}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name){
        this.id=id;
        this.name=name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList{
    //头指针，执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head; //默认为null

    //添加雇员到链表
    //说明
    //1.假定添加雇员的时候，id的分配总是从小到大，因此直接将雇员添加到链表的最后
    public void add(Emp emp){
        //如果是添加第一个雇员
        if (head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true){
            if(curEmp.next==null){
                break;
            }
            curEmp=curEmp.next; //后移
        }
        //退出时，直接将emp加入链表即可
        curEmp.next=emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head == null){ //说明链表为空
            System.out.println("第 "+no+" 条链表为空");
            return;
        }
        System.out.print("第 "+no+" 条链表的信息为");
        Emp curEmp=head; //辅助指针
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next==null){ //说明curEmp已经是最后的节点
                break;
            }
            curEmp=curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp，否则返回为空
    public Emp findEmpById(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp=head;
        while (true){
            if(curEmp.id==id){ //找到
                break; //这是curEmp就指向要查找的雇员
            }
            //退出
            if (curEmp.next==null){ //说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
        }
        return curEmp;

    }

}