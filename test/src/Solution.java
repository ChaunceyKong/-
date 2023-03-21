import java.util.Arrays;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public static ListNode partition(ListNode head, int x) {
        ListNode dummyHeadMin = new ListNode();
        ListNode dummyHeadMax = new ListNode();
        ListNode curMin = dummyHeadMin;
        ListNode curMax = dummyHeadMax;
        ListNode temp = head;

        while (temp != null) {
            if (temp.val < x) {
                curMin.next=temp;
                temp = temp.next;
                curMin = curMin.next;
            }
            else if (temp.val >= x) {
                curMax.next=temp;
                temp = temp.next;
                curMax = curMax.next;
            }

        }

        curMin.next = dummyHeadMax.next;

        return dummyHeadMin.next;
    }

    public static void main(String[] args) {
        ListNode n1=new ListNode(1);
        ListNode n2=new ListNode(4);
        ListNode n3=new ListNode(3);
        ListNode n4=new ListNode(2);
        ListNode n5=new ListNode(5);
        ListNode n6=new ListNode(2);

        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;

        ListNode head=new ListNode();
        head=Solution.partition(n1,3);

    }
}
