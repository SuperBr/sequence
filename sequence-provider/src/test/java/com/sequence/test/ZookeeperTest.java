package com.sequence.test;


import com.sequence.main.MainApplication;
import com.sequence.main.util.ZookeeperUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MainApplication.class)
@RunWith(SpringRunner.class)
public class ZookeeperTest {

    @Test
    public void zookeeperUtilTest() throws Exception {
        System.out.println(ZookeeperUtil.getInstance().getData().forPath("/").toString());

        while (true) {
            synchronized (ZookeeperTest.class) {
                ZookeeperTest.class.wait();
            }
        }

    }


}