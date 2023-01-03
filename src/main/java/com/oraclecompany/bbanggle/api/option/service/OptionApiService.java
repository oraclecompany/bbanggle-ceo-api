package com.oraclecompany.bbanggle.api.option.service;

import com.oraclecompany.bbanggle.api.option.dto.ProductOptionGroupDetailDto;
import com.oraclecompany.bbanggle.api.option.dto.ProductOptionListDto;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import com.oraclecompany.bbanggle.domain.product.service.ProductOptionGroupService;
import com.oraclecompany.bbanggle.domain.product.service.ProductOptionItemService;
import com.oraclecompany.bbanggle.domain.product.service.ProductService;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OptionApiService {

    private final ProductOptionGroupService productOptionGroupService;
    private final ProductOptionItemService productOptionItemService;
    private final StoreService storeService;

    public Page<ProductOptionListDto.Response> getProductOptionList(Long storeId, Pageable pageable) {
        Store store = storeService.findStoreById(storeId);
        Page<ProductOptionGroup> productOptionGroups = productOptionGroupService.findProductOptionList(pageable, store);
        List<ProductOptionListDto.Response> productOptionListResponseDtos =
                productOptionGroups.stream()
                        .map(ProductOptionListDto.Response::of)
                        .toList();
        return new PageImpl<>(productOptionListResponseDtos, pageable, productOptionGroups.getTotalElements());
    }

    public ProductOptionGroupDetailDto.Response getProductOptionGroupDetails(Long productOptionGroupId) {
        ProductOptionGroup productOptionGroup = productOptionGroupService.findProductOptionGroupById(productOptionGroupId);
        return ProductOptionGroupDetailDto.Response.of(productOptionGroup);
    }

    @Transactional
    public void updateProductOptionItemSellStatus(Long itemId) {
        ProductOptionItem findProductOptionItem = productOptionItemService.findProductOptionItem(itemId);
        findProductOptionItem.toggleSellStatus();
    }
}
