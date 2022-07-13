package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.Item;
import com.flabedu.small.small.dao.ItemDetail;
import com.flabedu.small.small.dao.enums.SizeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemMapperTest {

    @Autowired
    ItemMapper itemMapper;

    @Test
    @DisplayName("아이템 아이디로 아이템을 찾는다. 찾은 아이템의 id가 동일하다.")
    public void getItemTest(){
        //given
        long id = 10;

        //when
        var item = itemMapper.findItemById(id);

        //then
        Assertions.assertEquals(item.getItemId(), id);
    }

    @Test
    @DisplayName("id와 사이즈로 해당 아이템을 찾는다. 찾은 상세 아이템의 아이디와 사이즈가 동일하다.")
    public void getItemDetailTest(){
        // given
        var itemId = 10;
        var size = SizeEnum.S;

        // when
        var item = itemMapper.findItemDetail(itemId, size);

        // then
        Assertions.assertEquals(itemId, item.getItemId());
        Assertions.assertEquals(size, item.getSize());
    }

    @Test
    @DisplayName("상세 아이템 아이디로 상세 아이템을 찾는다. 찾은 아이디가 동일하다.")
    public void getItemDetailByIdTest(){
        //given
        var detailId = 4;

        //when
        var item = itemMapper.findItemDetailById(detailId);

        //then
        Assertions.assertEquals(detailId, item.getItemDetailId());
    }

    @Test
    @DisplayName("아이템 재고를 설정 후 해당 아이템 재고가 설정되었는지 확인한다.")
    public void setStockTest(){
        //given
        var detailId = 4;
        var stock = (long)(Math.random()*100);
        itemMapper.setStock(detailId, stock);

        //when
        var item = itemMapper.findItemDetailById(detailId);

        //then
        Assertions.assertEquals(stock, item.getStock());
    }

    @Test
    @DisplayName("아이템 아이디로 아이템을 찾는다. 찾은 아이템의 재고와 사이즈가 입력한 값과 동일하다.")
    public void findItemDetailByItemIdTest(){
        //given
        Item dummyItem = Item.builder()
                .name("dummy")
                .engName("dummy")
                .gender("C")
                .price(BigDecimal.valueOf(100))
                .registUserid("test")
                .registDate(LocalDateTime.now())
                .modifiedUserid("test")
                .modifiedDate(LocalDateTime.now())
                .build();

        List<ItemDetail> dummyItemDetails = new ArrayList<>();
        dummyItemDetails.add(ItemDetail.builder()
                .size(SizeEnum.L)
                .stock(11)
                .build()
        );

        itemMapper.addItem(dummyItem);
        itemMapper.addItemDetail(dummyItem.getItemId(), dummyItemDetails);

        //when
        var items =itemMapper.findItemDetailByItemId(dummyItem.getItemId());

        //then
        var expect = dummyItemDetails.get(0);
        var item =  items.get(items.size()-1);
        Assertions.assertEquals(expect.getSize(), item.getSize());
        Assertions.assertEquals(expect.getStock(), item.getStock());
    }

    @Test
    @DisplayName("아이템 아이디로 이미지 이름을 읽어온다. 읽어오기 전에 삽입한 이미지 이름과 동일하다.")
    public void findItemImagesNameByItemIdTest(){
        //given
        Item dummyItem = Item.builder()
                .name("dummy")
                .engName("dummy")
                .gender("C")
                .price(BigDecimal.valueOf(100))
                .registUserid("test")
                .registDate(LocalDateTime.now())
                .modifiedUserid("test")
                .modifiedDate(LocalDateTime.now())
                .build();

        List<ItemDetail> dummyItemDetails = new ArrayList<>();
        dummyItemDetails.add(ItemDetail.builder()
                .size(SizeEnum.L)
                .stock(11)
                .build()
        );
        List<String> dummyImageNames = List.of("A","B","C");
        itemMapper.addItem(dummyItem);
        itemMapper.addItemImage(dummyItem.getItemId(), dummyImageNames);

        //when
        var imageNames = itemMapper.findItemImagesNameByItemId(dummyItem.getItemId());

        //then
        Assertions.assertIterableEquals(dummyImageNames, imageNames);
    }

    @Test
    @DisplayName("카테고리가 ID가 3인 반팔티 카테고리 아이템을 DB에 넣은 후 해당 아이템 ID로 읽어온 데이터의 카테고리가 반팔 티셔츠이다.")
    public void getCategoryInfoTest(){
        //given
        Item dummyItem = Item.builder()
                .name("dummy")
                .engName("dummy")
                .gender("C")
                .price(BigDecimal.valueOf(100))
                .registUserid("test")
                .registDate(LocalDateTime.now())
                .modifiedUserid("test")
                .modifiedDate(LocalDateTime.now())
                .build();

        List<ItemDetail> dummyItemDetails = new ArrayList<>();
        dummyItemDetails.add(ItemDetail.builder()
                .size(SizeEnum.L)
                .stock(11)
                .build()
        );
        itemMapper.addItem(dummyItem);
        itemMapper.addItemCategory(dummyItem, 3L);

        //when
        var categoryInfo = itemMapper.getCategoryInfo(dummyItem.getItemId());

        //then
        Assertions.assertEquals("반팔 티셔츠", categoryInfo.get(0).getSubCategory());
    }
}
