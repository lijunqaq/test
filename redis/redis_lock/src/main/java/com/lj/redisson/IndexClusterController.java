package com.lj.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/10/21
 */
@RestController
public class IndexClusterController {

    private static final Logger logger = LoggerFactory.getLogger(IndexClusterController.class);

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/deduct_stock")
    public String testSentinel()  {
        String rediskey="stock";
        //锁的key
        String lockKey = "product_001";

        RLock redissonLock = redisson.getLock(lockKey);
        try {
            //加锁 实现续命功能
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(rediskey)); // jedis.get("stock")
            if (stock > 0) {
                Integer realstock = stock - 1;
                stringRedisTemplate.opsForValue().set(rediskey, realstock.toString());
                System.out.println("扣减成功，剩余库存：" + realstock + "");
            } else {
                System.out.println("扣减失败，库存不足");
            }
        }finally {
            redissonLock.unlock();
        }
        return "end";

    }


}
