package search;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args){
        int[] arr={1,8,10,89,1000,1000,1000,1234};
//        int resIndex=binarySearch(arr,0,arr.length-1,88);
//
//        System.out.println("下标为："+resIndex);
        List<Integer> resIndexList=binarySearch2(arr,0,arr.length-1,1000);
        System.out.println(resIndexList);
    }

    //二分查找法
    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){
        //当left>right时，说明递归整个数组，仍然没有找到该值
        if(left>right){
            return -1;
        }

        int mid=(left+right)/2;
        int midVal = arr[mid];

        if(findVal>midVal){ //向右递归
            return binarySearch(arr, mid+1, right, findVal);
        }
        else if(findVal<midVal){ //向左递归
            return binarySearch(arr, left, mid-1, findVal);
        }
        else{ //正好为中间值
            return mid;
        }
    }

    //改进的二分查找
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        //当left>right时，说明递归整个数组，仍然没有找到该值
        if(left>right){
            return new ArrayList<Integer>();
        }

        int mid=(left+right)/2;
        int midVal = arr[mid];

        if(findVal>midVal){ //向右递归
            return binarySearch2(arr, mid+1, right, findVal);
        }
        else if(findVal<midVal){ //向左递归
            return binarySearch2(arr, left, mid-1, findVal);
        }
        else{ //正好为中间值
            //思路分析
            //1.再找到mid索引值，不要马上返回
            //2.向mid索引值的左边扫描，将所有满足1000的元素下标，加入到集合ArrayList
            //3.向mid索引值的右边扫描，将所有满足1000的元素下标，加入到集合ArrayList
            //4.将ArrayList返回

            List<Integer> resIndexList=new ArrayList<Integer>();
            //向mid索引值的左边扫描，将所有满足1000的元素下标，加入到ArrayList
            int temp=mid-1;
            while(true){
                if (temp<0 || arr[temp] != findVal){
                    break;
                }
                //否则，就temp放入到resIndexList
                resIndexList.add(temp);
                temp-=1;
            }
            resIndexList.add(mid);

            //向mid索引值的右边扫描，将所有满足1000的元素下标，加入到ArrayList
            temp=mid+1;
            while(true){
                if (temp>arr.length-1 || arr[temp] != findVal){
                    break;
                }
                //否则，就temp放入到resIndexList
                resIndexList.add(temp);
                temp+=1;
            }
            return resIndexList;
        }
    }
}








