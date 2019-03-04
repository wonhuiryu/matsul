package com.wonhui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@TestConfiguration
public class BlogApplicationTests {


    private String aa;


    @Test
    public void contextLoads() {
        System.out.print(UUID.randomUUID().toString().replaceAll("-","").substring(0,15));
    }



}
