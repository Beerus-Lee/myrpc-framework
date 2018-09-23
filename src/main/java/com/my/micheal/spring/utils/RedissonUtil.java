package com.my.micheal.spring.utils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;;

public class RedissonUtil {

    private static RedissonClient redissonClient;

    public static RedissonClient createRedisConnection(String address) {
        Config config = new Config();
        //指定使用单节点部署方式
        config.useSingleServer().setAddress("redis://"+address);
        //创建客户端(发现这一非常耗时，基本在2秒-4秒左右)
        try {

            redissonClient = Redisson.create(config);
            System.out.println("连接redis成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redissonClient;
    }


    public static void shutDownRedisConnection() {
        if(redissonClient != null) {
            redissonClient.shutdown();
            System.out.println("redis shutdown");
        }
    }

}
