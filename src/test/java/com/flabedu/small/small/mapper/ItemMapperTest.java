package com.flabedu.small.small.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemMapperTest {

    @Autowired
    ItemMapper itemMapper;

    @Test
    public void listTest(){
        itemMapper.list();
    }

    @Test
    public void getItemTest(){
        long id = 1;

        var item = itemMapper.findItemById(id);

        Assertions.assertEquals(item.getItemId(), id);
    }
}
