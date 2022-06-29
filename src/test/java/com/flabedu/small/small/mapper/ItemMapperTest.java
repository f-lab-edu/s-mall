package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.enums.SizeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    List<ItemDetail> dummyItemDetails;
    Item dummyItem;
    @BeforeEach
    void setUp() {
        dummyItem = Item.builder()
                .name("dummy")
                .engName("dummy")
                .gender("C")
                .price(BigDecimal.valueOf(100))
                .registUserid("test")
                .registDate(LocalDateTime.now())
                .modifiedUserid("test")
                .modifiedDate(LocalDateTime.now())
                .build();

        dummyItemDetails = new ArrayList<>();
        dummyItemDetails.add(ItemDetail.builder()
                .size(SizeEnum.L)
                .stock(11)
                .build()
        );
    }

    @Test
    @DisplayName("itemMapper의 findItemAll()을 호출 시 아이템 목록을 반환한다.")
    public void itemListTest(){
        var list = itemMapper.findItemAll();

        Assertions.assertNotNull(list);
    }

    @Test
    @DisplayName("아이템 아이디로 아이템을 찾는다. 찾은 아이템의 id가 동일하다.")
    public void getItemTest(){
        long id = 10;

        var item = itemMapper.findItemById(id);

        Assertions.assertEquals(item.getItemId(), id);
    }
    
    @Test
    @DisplayName("itemMapper의 findItemDetailAll()을 호출 시 상세아이템을 반환한다.")
    public void itemDetailListTest(){
        var list = itemMapper.findItemDetailAll();

        Assertions.assertNotNull(list);
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
    //////////////////////////////////////////

    @Test
    @DisplayName("아이템 아이디로 아이템을 찾는다. 찾은 아이템의 재고와 사이즈가 입력한 값과 동일하다.")
    public void findItemDetailByItemIdTest(){
        itemMapper.addItem(dummyItem);
        itemMapper.addItemDetail(dummyItem.getItemId(), dummyItemDetails);

        var items =itemMapper.findItemDetailByItemId(dummyItem.getItemId());

        var expect = dummyItemDetails.get(0);
        var item =  items.get(items.size()-1);
        Assertions.assertEquals(expect.getSize(), item.getSize());
        Assertions.assertEquals(expect.getStock(), item.getStock());
    }

    @Test
    @DisplayName("아이템 아이디로 이미지 이름을 읽어온다. 읽어오기 전에 삽입한 이미지 이름과 동일하다.")
    public void findItemImagesNameByItemIdTest(){
        List<String> dummyImageNames = List.of("A","B","C");
        itemMapper.addItem(dummyItem);
        itemMapper.addItemImage(dummyItem.getItemId(), dummyImageNames);

        var imageNames = itemMapper.findItemImagesNameByItemId(dummyItem.getItemId());

        Assertions.assertIterableEquals(dummyImageNames, imageNames);
    }

    @Test
    @DisplayName("아이템을 반환한다. 에러 없이 성공한다.")
    public void getCategoryInfoTest(){
        itemMapper.addItem(dummyItem);
        itemMapper.addItemCategory(dummyItem, 2l);

        var categoryInfo = itemMapper.getCategoryInfo(dummyItem.getItemId());

        Assertions.assertEquals(categoryInfo.get(0), categoryInfo.get(0));
    }
}
