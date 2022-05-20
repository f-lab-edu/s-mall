package com.flabedu.small.small.service.mapper_test;

import com.flabedu.small.small.data.ItemDetail;
import com.flabedu.small.small.repository.ItemDetailMapper;
import com.flabedu.small.small.repository.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemDetailMapperTest {

    @Autowired
    ItemDetailMapper itemDetailMapper;

    @Test
    public void listTest(){
        itemDetailMapper.list();
    }

    @Test
    public void getItemDetailTest(){
        var itemId = 1;
        var size = "소";

        var item = itemDetailMapper.getItemDetail(itemId, size);

        Assertions.assertEquals(itemId, item.getItemId());
        Assertions.assertEquals(size, item.getSize());
    }

    @Test
    public void getItemDetailByIdTest(){
        var detailId = 1;
        var item = itemDetailMapper.getItemDetailById(detailId);
        Assertions.assertEquals(detailId, item.getItemDetailId());
    }

    @Test
    public void setStockTest(){
        var detailId = 1;
        var stock = (long)(Math.random()*100);

        itemDetailMapper.setStock(detailId, stock);

        var item = itemDetailMapper.getItemDetailById(detailId);
        Assertions.assertEquals(stock, item.getStock());
    }

}
