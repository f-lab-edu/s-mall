package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.enums.SizeEnum;
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
    public void itemListTest(){
        itemMapper.findItemAll();
    }

    @Test
    @DisplayName("아이템 아이디로 아이템을 찾는다. 찾은 아이템의 id가 동일하다.")
    public void getItemTest(){
        long id = 10;

        var item = itemMapper.findItemById(id);

        Assertions.assertEquals(item.getItemId(), id);
    }
    
    @Test
    @DisplayName("상세아이템을 반환한다. 에러 없이 성공한다.")
    public void itemDetailListTest(){
        itemMapper.findItemDetailAll();
    }

    @Test
    @DisplayName("id와 사이즈로 해당 아이템을 찾는다. 찾은 상세 아이템의 아이디와 사이즈가 동일하다.")
    public void getItemDetailTest(){
        var itemId = 10;
        var size = SizeEnum.S;

        var item = itemMapper.findItemDetail(itemId, size);

        Assertions.assertEquals(itemId, item.getItemId());
        Assertions.assertEquals(size, item.getSize());
    }

    @Test
    @DisplayName("상세 아이템 아이디로 상세 아이템을 찾는다. 찾은 아이디가 동일하다.")
    public void getItemDetailByIdTest(){
        var detailId = 4;
        var item = itemMapper.findItemDetailById(detailId);
        Assertions.assertEquals(detailId, item.getItemDetailId());
    }

    @Test
    @DisplayName("아이템 재고를 설정 후 해당 아이템 재고가 설정되었는지 확인한다.")
    public void setStockTest(){
        var detailId = 4;
        var stock = (long)(Math.random()*100);

        itemMapper.setStock(detailId, stock);

        var item = itemMapper.findItemDetailById(detailId);
        Assertions.assertEquals(stock, item.getStock());
    }
}
