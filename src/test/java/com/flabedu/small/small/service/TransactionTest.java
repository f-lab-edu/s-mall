package com.flabedu.small.small.service;

import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.enums.GenderEnum;
import com.flabedu.small.small.model.enums.SizeEnum;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.web.dto.request.ItemDetailRequestDTO;
import com.flabedu.small.small.web.dto.request.ItemRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TestService testService;
    @Autowired
    private TestSubService testSubService;

    private ItemDetailRequestDTO itemDetail;
    private ItemRequestDTO item;
    private int countAllItems;
    private int countAllItemDetails;

    @BeforeEach
    void setUp() {
        itemDetail = ItemDetailRequestDTO.builder()
                .size(SizeEnum.M).stock(100l).build();

        item = ItemRequestDTO.builder()
                .itemNameEn(null)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
                .itemDetails(Arrays.asList(itemDetail))
                .build();

        countAllItems = itemRepository.countAllItems();
        countAllItemDetails = itemRepository.countAllItemDetails();
    }

    @Test
    @DisplayName("REQUIRED")
    public void addItemRequired(){
        item.setItemName("tx propagation required");
        testService.addItemRequired(item);
        System.out.println(countAllItems + " -> " + itemRepository.countAllItems());
        System.out.println(countAllItemDetails + " -> " + itemRepository.countAllItemDetails());
    }

    @Test
    @DisplayName("REQUIRES_NEW")
    public void addItemRequiresNew(){
        item.setItemName("tx propagation requires_new");
        testService.addItemRequiresNew(item);
        assertThat(itemRepository.countAllItems()).isNotEqualTo(countAllItems);
        assertThat(itemRepository.countAllItemDetails()).isEqualTo(countAllItemDetails);
    }

    @Test
    @DisplayName("NESTED")
    public void addItemNested(){
        item.setItemName("tx propagation nested");
        testService.addItemNested(item);
        assertThat(itemRepository.countAllItems()).isNotEqualTo(countAllItems);
        assertThat(itemRepository.countAllItemDetails()).isEqualTo(countAllItemDetails);
    }

    @Test
    @DisplayName("NESTED PARENT")
    public void addItemNestedParentRollback(){
        item.setItemName("tx propagation nested parent");
        testService.addItemNestedParentRollback(item);
        assertThat(itemRepository.countAllItems()).isEqualTo(countAllItems);
        assertThat(itemRepository.countAllItemDetails()).isEqualTo(countAllItemDetails);
    }

    @Test
    @DisplayName("MANDATORY")
    public void addItemMandatory(){
        item.setItemName("tx propagation mandatory");
        testService.addItemMandatory(item);
        assertThat(itemRepository.countAllItems()).isEqualTo(countAllItems);
        assertThat(itemRepository.countAllItemDetails()).isEqualTo(countAllItemDetails);
    }

    @Test
    @DisplayName("SUPPORTS")
    public void addItemSupports(){
        item.setItemName("tx propagation supports");
        testService.addItemSupports(item);
        assertThat(itemRepository.countAllItems()).isEqualTo(countAllItems);
        assertThat(itemRepository.countAllItemDetails()).isEqualTo(countAllItemDetails);
    }

    @Test
    @DisplayName("NOT SUPPORTED")
    public void addItemNotSupported(){
        item.setItemName("tx propagation not supported");
        testService.addItemNotSupported(item);
        assertThat(itemRepository.countAllItems()).isNotEqualTo(countAllItems);
        assertThat(itemRepository.countAllItemDetails()).isNotEqualTo(countAllItemDetails);
    }

    @Test
    @DisplayName("NEVER")
    public void addItemNever(){
        item.setItemName("tx propagation never");
        testService.addItemNever(item);
        assertThat(itemRepository.countAllItems()).isEqualTo(countAllItems);
        assertThat(itemRepository.countAllItemDetails()).isEqualTo(countAllItemDetails);

    }
}
