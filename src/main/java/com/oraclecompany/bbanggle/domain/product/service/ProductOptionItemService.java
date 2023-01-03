package com.oraclecompany.bbanggle.domain.product.service;

import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import com.oraclecompany.bbanggle.domain.product.repository.ProductOptionItemRepository;
import com.oraclecompany.bbanggle.global.error.exception.EntityNotFoundException;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductOptionItemService {

    private final ProductOptionItemRepository productOptionItemRepository;

    public ProductOptionItem findProductOptionItem(Long itemId) {
        return productOptionItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_PRODUCT_OPTION_ITEM));
    }
}
