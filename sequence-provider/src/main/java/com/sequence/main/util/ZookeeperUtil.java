package com.sequence.main.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Component
public class ZookeeperUtil {
    private static CuratorFramework instance;
    /**
     * 连接地址
     */
    private static  String  connectionInfo;

    /**
     * 机房编号
     */
    private static  Integer  machineRoomOrder;
    /**
     * 服务编号
     */
    private static  Integer  providerNum;

    private static  final  String BASE_NAME="base/suber/sequence";
    public static CuratorFramework getInstance() throws Exception {
        if (instance == null) {
            synchronized (ZookeeperUtil.class) {
                if (instance == null) {
                    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
                    instance =
                            CuratorFrameworkFactory.builder()
                                    .connectString(connectionInfo)
                                    .sessionTimeoutMs(5000)
                                    .connectionTimeoutMs(5000)
                                    .retryPolicy(retryPolicy)
                                    .namespace(BASE_NAME)
                                    .build();
                    instance.start();
                    initClient(instance);
                }
            }
        }
        return instance;
    }


    private static void initClient(CuratorFramework client) throws Exception {
        if (StringUtils.isEmpty(machineRoomOrder)) {
            throw new Exception("machineRoomOrder is not existence！");
        }
        if (StringUtils.isEmpty(providerNum)) {
            throw new Exception("providerNum is not existence！");
        }
        client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/"+machineRoomOrder.toString()+"/"+providerNum.toString());
    }

    @Value("${zookeeper.connectionInfo}")
    public void setConnectionInfo(String connectionInfo) {
        ZookeeperUtil.connectionInfo = connectionInfo;
    }
    @Value("${provier.providerNum}")
    public  void setProviderNum(Integer providerNum) {
        ZookeeperUtil.providerNum = providerNum;
    }
    @Value("${provier.machineRoomOrder}")
    public void setMachineRoomOrder(Integer machineRoomOrder) {
        ZookeeperUtil.machineRoomOrder = machineRoomOrder;
    }


}