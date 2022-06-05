package com.flabedu.small.small.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemDetailMapperTest {

    @Autowired
    ItemDetailMapper itemDetailMapper;

    @Test
    @DisplayName("상세아이템을 반환한다. 에러 없이 성공한다.")
    public void listTest(){
        itemDetailMapper.list();
    }

    @Test
    @DisplayName("id와 사이즈로 해당 아이템을 찾는다. 찾은 상세 아이템의 아이디와 사이즈가 동일하다.")
    public void getItemDetailTest(){
        var itemId = 1;
        var size = "소";

        var item = itemDetailMapper.findItemDetail(itemId, size);

        Assertions.assertEquals(itemId, item.getItemId());
        Assertions.assertEquals(size, item.getSize());
    }

    @Test
    @DisplayName("상세 아이템 아이디로 상세 아이템을 찾는다. 찾은 아이디가 동일하다.")
    public void getItemDetailByIdTest(){
        var detailId = 1;
        var item = itemDetailMapper.findItemDetailById(detailId);
        Assertions.assertEquals(detailId, item.getItemDetailId());
    }

    @Test
    @DisplayName("아이템 재고를 설정 후 해당 아이템 재고가 설정되었는지 확인한다.")
    public void setStockTest(){
        var detailId = 1;
        var stock = (long)(Math.random()*100);

        itemDetailMapper.setStock(detailId, stock);

        var item = itemDetailMapper.findItemDetailById(detailId);
        Assertions.assertEquals(stock, item.getStock());
    }

}
