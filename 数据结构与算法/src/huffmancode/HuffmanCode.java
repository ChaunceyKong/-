package huffmancode;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length); //40

        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是"+Arrays.toString(huffmanCodesBytes));

        byte[] sourceBytes = decode(huffmanCodes,huffmanCodesBytes);
        System.out.println("原来的字符串=" + new String(sourceBytes));

        //分布过程
        /*

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);

        //测试一把创建huffman二叉树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        preOrder(huffmanTreeRoot);

        //测试一把是否生成了哈夫曼编码
        Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
        //System.out.println("生成的哈夫曼编码表"+huffmanCodes);

        //测试
        byte[] huffmanCodesBytes = zip(contentBytes,huffmanCodes);
        System.out.println("huffmanCodesBytes="+Arrays.toString(huffmanCodesBytes));
         */

    }

    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes数组 重新转成Huffman编码对应的二进制字符串
    //2.将Huffman编码对应的二进制字符串 转成原始字符串

    //编写一个方法，完成对压缩数据的解码
    /**
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到 huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag,b));
        }

        //把字符串按照指定的赫夫曼编码表进行解码
        //把赫夫曼编码表进行调换，因为反向查询a->100 100->?
        Map<String,Byte> map = new HashMap<String,Byte>();
        for (Map.Entry<Byte,String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解为就是索引，扫描stringBuilder
        for (int i=0; i<stringBuilder.length();) {
            int count = 1; //小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //递增取出'1'/'0'
                String key = stringBuilder.substring(i,i+count); //i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                }
                else{ //匹配到
                    flag=false;
                }
            }
            list.add(b);
            i += count; //i 移动到count
        }
        //当for循环结束后，list中就存放了所有原始字符串
        //把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转成一个二进制的字符串
     * @param b 传入的byte
     * @param flag 标志是否需要补高位，如果是true表示需要补，如果是false表示不需要补
     * @return 是该b 对应的二进制字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b; //将 b 转成int
        //如果是正数，我们还存在补高位的问题
        if (flag) {
            temp |= 256; //按位或 1 0000 0000
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制补码
        if (flag) {
            return str.substring(str.length()-8);
        }
        else {
            return str;
        }

    }

    //使用一个方法，将前面的方法封装起来，便于我们调用
    /**
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 返回的是经过huffman编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据创建的nodes 创建huffman二叉树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据huffman树，生成哈夫曼编码
        Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据huffman编码，得到压缩后的huffman编码压缩数组
        byte[] huffmanCodesBytes = zip(bytes,huffmanCodes);
        return huffmanCodesBytes;
    }

    //编写一个方法，通过生成的huffman编码表，将一个字符串对应的byte[] 返回压缩码
    /**
     *
     * @param bytes 原始字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例："i like like like java do you like a java" 的byte[] ===> "10101000...."的byte[]，即
     * huffmanCodeByte[0]= 10101000(补码) ==> byte [推导 10101000 => 10101000-1 => 10100111(反码) => 11011000=-88]
     * huffmanCodeByte[0]=-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes 将 bytes 转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //将赫夫曼编码字符串"101010001011111..." 转成 byte[]
        //统计返回 byte[] huffmanCodeByte 长度
        //一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length()/8;
        }
        else {
            len = stringBuilder.length()/8 + 1;
        }
        //创建存储压缩后的byte[]
        byte[] huffmanCodeByte = new byte[len];
        int index = 0; //记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i+=8) {//因为是每8位对应一个byte，所以步长+8
            String strByte;
            if (i+8>stringBuilder.length()){ //不够8位
                strByte = stringBuilder.substring(i);
            }
            else {
                strByte = stringBuilder.substring(i,i+8);
            }

            //将strByte转成一个byte，放入到huffmanCodeByte
            huffmanCodeByte[index] = (byte)Integer.parseInt(strByte,2);
            index++;

        }
        
        return huffmanCodeByte;
    }

    //生成赫夫曼树对应的赫夫曼编码
    //思路：
    //1. 将赫夫曼编码表存放在 Map<Byte,String>，形式：32->01  97->100  100->11000 ...
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    //2.在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder  存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载getCodes方法
    private static Map<Byte,String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);

        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node 传入结点
     * @param code 路径：左子结点是0 右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder1.append(code);
        if (node != null) { //如果node==null 不处理
            //判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {
                //递归处理
                //向左递归
                getCodes(node.left,"0",stringBuilder1);
                //向右递归
                getCodes(node.right,"1",stringBuilder1);
            }
            else {
                //就表示找到某个叶子结点
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root != null){
            root.preOrder();
        }
        else{
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     *
     * @param bytes 接收字节数组
     * @return 返回的就是List，形式[Node[data=97, weight=5], Node[data=32, weight=9], ......]
     */
    public static List<Node> getNodes(byte[] bytes) {
        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();
        //遍历bytes，统计每一个byte出现的次数->map[key,value]
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { //Map还没有这个字符数据，第一次
                counts.put(b,1);
            }
            else {
                counts.put(b,count+1);
            }
        }

        //每把一个键值对转成一个Node对象，并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte,Integer> entry: counts.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //可以通过List 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取出最小的一棵二叉树
            Node leftNode = nodes.get(0);
            //取出第二小的一棵二叉树
            Node rightNode = nodes.get(1);
            //创建一棵新的二叉树，它的根结点没有data，只有权值
            Node parent = new Node(null,leftNode.weight+rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的两棵二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

//创建Node，带数据和权值
class Node implements Comparable<Node> {
    Byte data; // 存放字符本身，比如'a'=>97
    Integer weight; // 权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, Integer weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}