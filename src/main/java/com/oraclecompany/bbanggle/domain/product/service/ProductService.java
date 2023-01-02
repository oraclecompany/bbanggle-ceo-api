package com.oraclecompany.bbanggle.domain.product.service;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionGroup;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionItem;
import com.oraclecompany.bbanggle.domain.product.entity.ProductOptionLink;
import com.oraclecompany.bbanggle.domain.product.repository.ProductOptionGroupRepository;
import com.oraclecompany.bbanggle.domain.product.repository.ProductOptionItemRepository;
import com.oraclecompany.bbanggle.domain.product.repository.ProductOptionLinkRepository;
import com.oraclecompany.bbanggle.domain.product.repository.ProductRepository;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.global.error.exception.EntityNotFoundException;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor    //생성자 주입, final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성
@Transactional              //트랜잭션 보장
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionGroupRepository productOptionGroupRepository;
    private final ProductOptionItemRepository productOptionItemRepository;

    public Page<Product> findProductList(Pageable pageable, Store store) {
        return productRepository.findByStore(store, pageable);
    }

    public Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_PRODUCT));
    }

    public Page<ProductOptionGroup> findProductOptionList(Pageable pageable, Store store) {
        return productOptionGroupRepository.findByStore(pageable, store);
    }

    public ProductOptionGroup findProductOptionGroupById(Long optionId) {
        return productOptionGroupRepository.findById(optionId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_PRODUCT_OPTION));
    }

    public ProductOptionGroup findProductOptionItemList(ProductOptionGroup productOptionGroup) {
        return productOptionGroupRepository.findByProductOptionGroup(productOptionGroup);
    }

    public ProductOptionItem findProductOptionItem(Long itemId) {
        return productOptionItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_PRODUCT_OPTION_ITEM));
    }
}
