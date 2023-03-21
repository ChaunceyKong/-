package search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxsize=20;
    public static void main(String[] args){
        int[] arr={1,8,10,89,1000,1234};
        System.out.println("index="+fibSearch(arr,1000));

    }

    //因为后面我们mid=low+F[k-1]-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib(){
        int[] f=new int[maxsize];
        f[0]=1;
        f[1]=1;
        for(int i=2;i<maxsize;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式编写算法
    /**
     *
     * @param arr 数组
     * @param key 需要查找的关键码（值）
     * @return 返回对应下标，没有返回-1
     */
    public static int fibSearch(int[] arr,int key){
        int low = 0;
        int high=arr.length-1;
        int k=0; //表示斐波那契分割数值的下标
        int mid=0;
        int[] f=fib(); //获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high>f[k]-1){
            k++;
        }
        //因为 F[k]值可能大于arr的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp= Arrays.copyOf(arr,f[k]);
        //实际上需要使用arr数组最后的数填充temp
        for (int i=high+1;i<temp.length;i++){
            temp[i]=arr[high];
        }
        //使用while来循环处理，找到我们的数key
        while(low<=high){ //只要这个条件满足就可以一直找
            mid=low+f[k-1]-1;
            if(key<temp[mid]){ //我们应该继续向数组左边查找
                high=mid-1;
                //为什么是 k--
                //说明
                //1.全部元素=前面的元素+后面的元素
                //2.F[k]=F[k-1]+F[k-2]
                //因为前面有F[k-1]个元素，所以可以继续拆分F[k-1]=F[k-2]+F[k-3]
                //即在F[k-1]的前面继续查找 k--
                //即下次循环mid=low+F[k-1-1]-1
                k--;
            }
            else if(key>temp[mid]){ //向数组右边查找
                low=mid+1;
                //为什么是 k-=2
                //说明
                //1.全部元素=前面的元素+后面的元素
                //2.F[k]=F[k-1]+F[k-2]
                //3.因为后面有F[k-2]个元素，所以可以继续拆分F[k-2]=F[k-3]+F[k-4]
                //即在F[k-2]的前面继续查找 k-=2
                //即下次循环mid=low+F[k-2-1]-1
                k-=2;
            }
            else{ //找到了
                //需要确定，返回的是哪个下标
                if(mid<=high){
                    return mid;
                }
                else{
                    return high;
                }
            }
        }
        return -1;
    }
}
