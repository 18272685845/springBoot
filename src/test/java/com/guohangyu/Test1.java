package com.guohangyu;

import com.guohangyu.entity.Tb_user;
import com.guohangyu.service.Tb_userService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {
    @Autowired
    private Tb_userService tb_userService;
    @Test
    public void test3(){
        Integer delete = tb_userService.delete(7);
        System.out.println(delete);
    }
}
