package org.wangling;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @program: easygo
 * @description:
 * @author: wangling
 * @create: 2018-09-07
 **/

public class DemoMain {
    public static void main(String[] args)  {

        ZooKeeper zk=null;
        try {
            zk = new ZooKeeper("192.168.0.106:2181", 10000, new Watcher() {
                //监控所有被触发的事件
                public void process(WatchedEvent event) {

                    System.out.println("已经触发了" + event.getType() + "事件");
                }
            });
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try{

            if(null==zk.exists("/testRootPath2", new Watcher() {
                public void process(WatchedEvent event) {
                    System.out.println("检测testRootPath2节点存在");
                }
            })){
                //创建一个目录节点
                zk.create("/testRootPath2",null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        //创建一个子节点
        try{
            if(null==zk.exists("/testRootPath2/testChildPathOne", new Watcher() {
                public void process(WatchedEvent event) {
                    System.out.println("检测testRootPath2/testChildPathOne节点存在");
                }
            })){
                zk.create("/testRootPath2/testChildPathOne",null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(zk.getData("/testRootPath1",false,null));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        try{
            zk.delete("/testRootPath1",-1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
