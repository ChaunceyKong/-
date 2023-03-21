package sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args){
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0,8000000)的数据
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是="+date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是="+date2Str);
    }

    public static void selectSort(int[] arr){
        //选择排序时间复杂度是O(n^2)
        for (int i = 0; i<arr.length-1; i++){
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j< arr.length;j++){
                if (min>arr[j]){ //说明假定的最小值并不是最小
                    min=arr[j]; //重置min
                    minIndex=j; //重置minIndex
                }
            }
            //将最小值放在arr[i]，即交换
            if(minIndex != i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
        }
    }
}
