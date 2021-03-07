package com.myspringboot.zookeeper.util;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 初始化连接的工具类
 */
public class ZKUtils {

    private static ZooKeeper zooKeeper;

    // /test节点需要自己手动创建，也可以不加/test，直接在/目录下做测试
    private static String address = "192.168.5.132:2181,192.168.5.133:2181,192.168.5.134:2181/test";

    private static WatcherImpl watcher = new WatcherImpl();

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZooKeeper getZK() {
        try {
            zooKeeper = new ZooKeeper(address, 3000, watcher);
            watcher.setCountDownLatch(countDownLatch);
            countDownLatch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
}
