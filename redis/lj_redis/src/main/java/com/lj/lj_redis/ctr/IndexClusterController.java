package com.lj.lj_redis.ctr;

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
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 测试节点挂了哨兵重新选举新的master节点，客户端是否能动态感知到
     * 新的master选举出来后，哨兵会把消息发布出去，客户端实际上是实现了一个消息监听机制，
     * 当哨兵把新master的消息发布出去，客户端会立马感知到新master的信息，从而动态切换访问的masteple
     *
     * @throws InterruptedException
     */
    @RequestMapping("/test_cluster")
    public void testSentinel() throws InterruptedException {
        int i = 1;
        while (true) {
            try {
                stringRedisTemplate.opsForValue().set("zhuge" + i, i + "");
                System.out.println("设置key：" + "zhuge" + i);
                i++;
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.error("错误：", e);
            }
        }
    }
}
