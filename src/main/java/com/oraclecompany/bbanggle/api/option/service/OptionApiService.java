package com.oraclecompany.bbanggle.api.option.service;

import com.oraclecompany.bbanggle.api.option.dto.ProductOptionListResponseDto;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionLink;
import com.oraclecompany.bbanggle.domain.product.service.ProductService;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OptionApiService {

    private final ProductService productService;
    private final StoreService storeService;

    public List<ProductOptionListResponseDto> getProductOptionList(Long storeId, Pageable pageable) {
        Store store = storeService.findStoreById(storeId);
        Page<ProductOptionGroup> productOptionGroups = productService.findProductOptionList(pageable, store);
        return productOptionGroups.stream()
                .map(ProductOptionListResponseDto::of)
                .toList();
    }
}
