package com.lj.schedule;

import java.util.concurrent.*;

/**
 * 定时线程池api使用
 *
 * @author Lj
 * @date 2019/12/5
 */
public class ScheduleTest {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);


        pool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟执行2s");
            }
        },2,TimeUnit.SECONDS);



        /**
         * 延迟 间隔执行  这个任务周期是固定的 不管任务执行时间多长 每3s产生一个任务
         */
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("重复执行s");
            }
        },1,3,TimeUnit.SECONDS);


        /**
         * 这个会等待上个任务结束 经过间隔时间（3s） 才会产生任务  如果执行任务耗时3s 则每6s产生一个任务
         */
        pool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("重复执行等待s");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1,3,TimeUnit.SECONDS);

      pool.shutdown();
    }
}
