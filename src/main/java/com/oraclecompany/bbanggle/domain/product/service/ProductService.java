package com.oraclecompany.bbanggle.domain.product.service;

import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.repository.ProductGroupLinkRepository;
import com.oraclecompany.bbanggle.domain.product.repository.ProductRepository;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.global.error.exception.EntityNotFoundException;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor    //생성자 주입, final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성
@Transactional              //트랜잭션 보장
@Service
public class ProductService {

    private final ProductGroupLinkRepository productGroupLinkRepository;
    private final ProductRepository productRepository;

    public Page<Product> findProductList(Pageable pageable, Store store) {
        return productRepository.findByStore(store, pageable);
    }

    public Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_PRODUCT));
    }


}
