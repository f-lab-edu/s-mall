package com.flabedu.small.small.service;

import com.flabedu.small.small.web.dto.request.ItemsProductDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTest {
    @Mock
    ItemService itemService;

    @Test
    void purchaseItem(){
        itemService.purchaseItem(new ItemsProductDTO(), "test");
    }

}
