package com.kong;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        //1.new Jedis 对象即可
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //jedis所有的命令就是redis的所有指令
        //测试连接
        System.out.println(jedis.ping());
    }
}
