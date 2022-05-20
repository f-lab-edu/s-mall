package com.flabedu.small.small.service.contract;

import com.flabedu.small.small.exception.ItemException;
import com.flabedu.small.small.exception.MemberException;
import com.flabedu.small.small.web.dto.request.ItemsProductDTO;

import java.util.List;

public interface ItemService {
    void purchaseItem(ItemsProductDTO itemInfo, String userID)
            throws ItemException, MemberException;
}
