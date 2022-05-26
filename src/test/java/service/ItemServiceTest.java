package service;

import com.flabedu.small.small.model.GenderEnum;
import com.flabedu.small.small.model.Item;
import com.flabedu.small.small.model.ItemDetail;
import com.flabedu.small.small.model.SizeEnum;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private ItemDetail itemDetail;
    private Item item;

    @BeforeEach
    void setUp() {
        itemDetail = ItemDetail.builder()
                .size(SizeEnum.M).stock(100l).build();

        ItemDTO item = ItemDTO.builder()
                .itemNameKr("test1")
                .itemNameEn(null)
                .subCategory(3l)
                .gender(GenderEnum.C)
                .price(new BigDecimal(20000))
                .itemImages(Arrays.asList("img1.png","img2.png"))
                .itemDetails(Arrays.asList(itemDetail))
                .build();
    }

    @Test
    @DisplayName("상품 등록")
    public void addItem_ValidData(){

        itemService.addItem(item);
        //Assertions.assertThatThrownBy(()->itemService.addItem(item)).isInstanceOf(CustomException.class);
    }

}
