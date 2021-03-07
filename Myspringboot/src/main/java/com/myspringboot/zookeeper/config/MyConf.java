package com.myspringboot.zookeeper.config;

/**
 * 回调函数与主线程数据互通的承载类
 */
public class MyConf {
    private String conf;

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }
}
