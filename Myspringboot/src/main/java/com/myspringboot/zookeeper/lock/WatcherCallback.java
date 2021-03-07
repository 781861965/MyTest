package com.myspringboot.zookeeper.lock;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 回调函数实现类
 */
public class WatcherCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback, AsyncCallback.StringCallback, AsyncCallback.ChildrenCallback {

    private ZooKeeper zooKeeper;

    private String threadName;

    private String pathName;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {

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
                break;
            case NodeDeleted:
                System.out.println(threadName + "  getChildren  again");
                zooKeeper.getChildren("/", false, this, "asd");
                break;
            case NodeDataChanged:

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

    public void tryLock() {
        try {
            System.out.println(threadName + "  tryLock");
            zooKeeper.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "asd");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void unLock() {
        try {
            zooKeeper.delete(pathName, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (name != null) {
            System.out.println(threadName + " create ok : " + name);
            pathName = name;
            zooKeeper.getChildren("/", false, this, "asd");
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        System.out.println(threadName + " children list size:  " + children.size());
        Collections.sort(children);
        int i = children.indexOf(pathName.substring(1));
        if (i == 0) {
            // 重入锁做准备
            try {
                zooKeeper.setData("/", threadName.getBytes(), -1);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        } else {
            zooKeeper.exists("/" + children.get(i - 1), this, this, "asd");
        }
    }
}
