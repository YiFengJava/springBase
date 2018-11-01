package com.test;

import com.myyd.ExeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest(classes = ExeApplication.class)
@RunWith(SpringRunner.class)
public class ExeTestApplication {

    @Test
    public void test()  {

        System.out.println(12);
    }
}
