package com.myspringboot.zookeeper.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * 回调函数实现类
 */
public class WatcherCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    private ZooKeeper zooKeeper;
    private MyConf myConf;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void setMyConf(MyConf myConf) {
        this.myConf = myConf;
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            myConf.setConf(new String(data));
            countDownLatch.countDown();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zooKeeper.getData("/asd", this, this, "zxc");
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                zooKeeper.getData("/asd", this, this, "zxc");
                break;
            case NodeDeleted:
                myConf.setConf("");
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zooKeeper.getData("/asd", this, this, "zxc");
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }

    }

    public void await() {
        zooKeeper.exists("/asd", this, this, "zxc");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
