package service;

import com.flabedu.small.small.domain.GenderEnum;
import com.flabedu.small.small.domain.Item;
import com.flabedu.small.small.domain.ItemDetail;
import com.flabedu.small.small.domain.SizeEnum;
import com.flabedu.small.small.exception.CustomException;
import com.flabedu.small.small.repository.ItemRepository;
import com.flabedu.small.small.service.ItemService;
import com.flabedu.small.small.web.dto.ItemDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    @DisplayName("상품 등록")
    public void addItem_ValidData(){
        ItemDetail itemDetail = ItemDetail.builder()
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

        itemService.addItem(item);
        //Assertions.assertThatThrownBy(()->itemService.addItem(item)).isInstanceOf(CustomException.class);
    }

}
