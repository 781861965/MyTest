package com.myspringboot.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperTest {
    public static void main(String[] args) {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ZooKeeper zooKeeper = new ZooKeeper("192.168.5.132:2181,192.168.5.133:2181,192.168.5.134:2181", 3000, new Watcher() {

                @Override
                public void process(WatchedEvent watchedEvent) {
                    Event.KeeperState state = watchedEvent.getState();
                    Event.EventType type = watchedEvent.getType();
                    String path = watchedEvent.getPath();
                    System.out.println(watchedEvent.toString());
                    switch (state) {
                        case Unknown:
                            break;
                        case Disconnected:
                            break;
                        case NoSyncConnected:
                            break;
                        case SyncConnected:
                            System.out.println("SyncConnected");
                            countDownLatch.countDown();
                            break;
                        case AuthFailed:
                            break;
                        case ConnectedReadOnly:
                            break;
                        case SaslAuthenticated:
                            break;
                        case Expired:
                            break;
                        case Closed:
                            break;
                    }

                    switch (type) {
                        case None:
                            break;
                        case NodeCreated:
                            break;
                        case NodeDeleted:
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
            });
            countDownLatch.await();
            ZooKeeper.States state = zooKeeper.getState();
            switch (state) {
                case CONNECTING:
                    System.out.println("CONNECTING");
                    break;
                case ASSOCIATING:
                    break;
                case CONNECTED:
                    System.out.println("CONNECTED");
                    break;
                case CONNECTEDREADONLY:
                    break;
                case CLOSED:
                    break;
                case AUTH_FAILED:
                    break;
                case NOT_CONNECTED:
                    break;
            }
            String pathName = zooKeeper.create("/zoo", "cat".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            Stat stat = new Stat();
            byte[] zoos = zooKeeper.getData("/zoo", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("zoo " + event.toString());
                    try {
                        byte[] data = zooKeeper.getData("/zoo", this, stat);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, stat);
            System.out.println(new String(zoos));
            System.out.println(stat.toString());
            Stat stat1 = zooKeeper.setData("/zoo", "heiheihei".getBytes(), 0);
            Stat stat2 = zooKeeper.setData("/zoo", "heiheihei".getBytes(), stat1.getVersion());

            System.out.println("----------------start-----------------------");
            zooKeeper.getData("/zoo", false, new AsyncCallback.DataCallback() {
                @Override
                public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                    System.out.println("===================go=======================");
                    System.out.println(new String(data));
                    System.out.println(ctx.toString());
                }
            }, "asd");
            System.out.println("-----------------end------------------------");
            Thread.sleep(99999L);

        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }


    }

}
