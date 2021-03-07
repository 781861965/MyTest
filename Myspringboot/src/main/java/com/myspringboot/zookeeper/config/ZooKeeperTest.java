package com.myspringboot.zookeeper.config;

import com.myspringboot.zookeeper.util.ZKUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试类
 */
public class ZooKeeperTest {

    ZooKeeper zooKeeper;

    @Before
    public void conn() {
        System.out.println("conn");
        zooKeeper = ZKUtils.getZK();
    }

    @After
    public void close() {
        System.out.println("close");
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConf() throws InterruptedException {
        WatcherCallback watcherCallback = new WatcherCallback();
        watcherCallback.setZooKeeper(zooKeeper);
        MyConf myConf = new MyConf();
        watcherCallback.setMyConf(myConf);
        watcherCallback.await();
        while (true) {
            if ("".equals(myConf.getConf())) {
                System.out.println("节点被删了");
                watcherCallback.await();
            }
            System.out.println(myConf.getConf());
            Thread.sleep(1000);
        }
    }
}
