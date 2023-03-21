package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args){
        int[] arr={101,34,119,1,-1,89};
        insertSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        int insertVal=0;
        int insertIndex=0;
        for (int i=1;i<arr.length;i++){
            //定义待插入的数
            insertVal=arr[i];
            insertIndex=i-1; //即arr[i]前面的这个数下标

            //给insertVal找到插入的位置
            //说明
            //1.insertIndex >=0保证在给insertVal找插入位置时不越界
            //2。insertVal < arr[insertIndex]待插入的数，还没有找到插入位置
            //3.就需要将arr[insertIndex]后移
            while(insertIndex >=0 && insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex]; //
                insertIndex--;
            }
            //当退出while循环时说明插入位置找到，为insertIndex+1
            arr[insertIndex+1]=insertVal;
        }
    }
}
