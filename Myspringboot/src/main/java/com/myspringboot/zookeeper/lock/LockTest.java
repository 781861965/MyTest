package com.myspringboot.zookeeper.lock;


import com.myspringboot.zookeeper.util.ZKUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试类
 */
public class LockTest {
    ZooKeeper zooKeeper;

    @Before
    public void conn() {
        zooKeeper = ZKUtils.getZK();
    }

    @After
    public void close() {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLock() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    WatcherCallback watcherCallback = new WatcherCallback();
                    watcherCallback.setZooKeeper(zooKeeper);
                    watcherCallback.setThreadName(threadName);
                    watcherCallback.tryLock();

                    System.out.println(threadName + " doing...");
                    //  假装业务耗时
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadName + " end...");
                    watcherCallback.unLock();
                }
            }.start();
        }

        try {
            Thread.sleep(9999L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
