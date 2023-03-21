package search;

public class InsertValueSearch {
    public static void main(String[] args){
        int[] arr=new int[100];

        for (int i=0;i<100;i++){
            arr[i]=i+1;
        }
        int index=insertValueSearch(arr,0,arr.length-1,1);
        System.out.println("数组下标为："+index);
    }

    //编写插值查找法
    //说明：插值查找算法也需要有序
    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return 如果找到就返回对应下表，如果没找到就返回-1
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        //findVal<arr[0]和findVal>arr[arr.length-1])是必须的，防止findVal过大或过小，mid导致数组越界
        if (left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }

        //求出mid
        int mid=left + (right - left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal=arr[mid];

        if(findVal>midVal){ //向右递归
            return insertValueSearch(arr,mid+1,right,findVal);
        }
        else if (findVal<midVal){ //向左递归
            return insertValueSearch(arr,left,mid-1,findVal);
        }
        else{
            return mid;
        }
    }
}
