package algorithm.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] data = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵关系使用二维数组描述
        int[][] weight = new int[][] {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,verxs,data,weight);
        //输出
        minTree.showGraph(graph);
        //测试普里姆算法
        minTree.prim(graph,0);
    }
}

//创建最小生成树 ->村庄
class MinTree {
    //创建图的邻接矩阵
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法，得到最小生成树
    /**
     *
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成'A'->0  'B'->1......
     */
    public void prim(MGraph graph, int v) {
        //visited[] 标记结点(顶点)是否被访问过
        int[] visited = new int[graph.verxs];
        //visited[] 默认元素都是0，表示没有访问过

        //把当前这个结点标记为已访问
        visited[v] = 1;
        //用h1 和 h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //将minWeight初始化成一个大的数，后面在被遍历过程中会被替换
        int minWeight = 10000;

        //因为有 graph.verxs个顶点，普里姆算法结束后，有graph.verxs-1个边
        for (int k = 1; k < graph.verxs; k++) {

            //这个是确定每一次生成的子图，和哪两个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) { //遍历已经访问过的结点
                for (int j = 0; j < graph.verxs; j++) { //遍历还未访问的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //此时找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight重新设置为最大值10000
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs; //表示图的结点个数
    char[] data; //存放结点数据
    int[][] weight; //存放边，就是我们的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
