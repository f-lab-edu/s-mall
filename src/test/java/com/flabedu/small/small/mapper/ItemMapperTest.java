package com.flabedu.small.small.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("아이템을 반환한다. 에러 없이 성공한다.")
    public void listTest(){
        itemMapper.list();
    }

    @Test
    @DisplayName("아이템 아이디로 아이템을 찾는다. 찾은 아이템의 id가 동일하다.")
    public void getItemTest(){
        long id = 1;

        var item = itemMapper.findItemById(id);

        Assertions.assertEquals(item.getItemId(), id);
    }
}
