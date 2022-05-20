package com.flabedu.small.small.service.mapper_test;

import com.flabedu.small.small.repository.ItemDetailMapper;
import com.flabedu.small.small.repository.ItemMapper;
import com.flabedu.small.small.repository.MemberMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    public void listTest(){
        memberMapper.list();
    }

    @Test
    public void getItemTest(){
        String id = "test_user";

        var item = memberMapper.getMember(id);

        Assertions.assertEquals(item.getUserId(), id);
    }
}
