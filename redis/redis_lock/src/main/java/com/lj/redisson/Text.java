package com.lj.redisson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/10/29
 */
public class Text {


    private static Logger logger = LoggerFactory.getLogger(Text.class);

    //模拟数据库
    private static Map<String, String> db = new HashMap<>();
    //模拟缓存
    private static Map<String, String> redis = new HashMap<>();

    public static void main(String[] args) {


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "lijunqaq.top", 6379, 3000, null);


        List<Jedis> minIdleJedisList = new ArrayList<Jedis>(jedisPoolConfig.getMinIdle());

        for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                minIdleJedisList.add(jedis);
                jedis.ping();
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            } finally {
                //注意这里不能把连接还给连接池 不然还回去又取出来 一直都只有一个连接  比如 取10个连接 不还回去 连接池就有10个了
                //jedis.close();
            }
        }

        //统一将预热的连接还回连接池
        for (int i = 0; i < jedisPoolConfig.getMinIdle(); i++) {
            Jedis jedis = null;
            try {
                jedis = minIdleJedisList.get(i);
                //将连接还回连接池
                jedis.close();
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            } finally {

            }

        }


    }


}
