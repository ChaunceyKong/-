package sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int[] arr={4,6,8,5,9,-1,3};
        heapSort(arr);
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        int temp=0;
        System.out.println("堆排序！！");

        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        //将堆顶元素与末尾元素交换，将最大元素“沉”到数组的末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int j=arr.length-1;j>0;j--) {
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            //由于只交换根节点和最后一个节点，因此刨除此时新的root节点，以第二层节点为父节点的树已经是大顶堆
            //故，只需要从i=0开始进行大顶堆构造即可
            adjustHeap(arr,0,j);
        }
        System.out.println("数组=" + Arrays.toString(arr));
    }

    //将一个数组（二叉树），调整成一个大顶堆
    /**
     * 完成将以i为父节点的树转换成大顶堆
     * 举例：arr={4,6,8,5,9}; => i=1 => adjustHeap => 得到{4,9,8,5,6}
     * 如果我们再次调用adjustHeap,传入的是i=0 => 得到{9,6,8,5,4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整，length在逐渐减少
     */
    public static void adjustHeap(int[] arr,int i,int length) {
        int temp=arr[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1.k是i节点的左子节点
        for (int k=2*i+1; k<length; k=k*2+1) {
            if (k+1<length && arr[k]<arr[k+1]) { //说明左子节点的值小于右子节点的值
                k++; //k指向右子节点
            }
            if (arr[k]>temp) { //如果子节点大于父节点
                arr[i]=arr[k]; //把较大的值赋给当前节点
                i=k; //!!! i指向k，继续循环比较 因为arr[k]位置元素变动，下次要重新对以k为父节点的树进行排序
            }
            else {
                break; //!!!
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值放在了父节点位置
        arr[i]=temp; //将temp的值放在调整后的位置
    }
}
