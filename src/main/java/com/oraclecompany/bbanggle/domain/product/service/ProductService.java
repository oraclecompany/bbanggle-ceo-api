package com.oraclecompany.bbanggle.domain.product.service;

import com.oraclecompany.bbanggle.api.product.dto.ProductQuantityUpdateDto;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductGroupLink;
import com.oraclecompany.bbanggle.domain.product.repository.ProductGroupLinkRepository;
import com.oraclecompany.bbanggle.domain.product.repository.ProductRepository;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.global.error.exception.EntityNotFoundException;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor    //생성자 주입, final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성
@Transactional              //트랜잭션 보장
@Service
public class ProductService {

    private final ProductGroupLinkRepository productGroupLinkRepository;
    private final ProductRepository productRepository;

    public Page<ProductGroupLink> selectProductList(Pageable pageable, Store store) {
        List<Product> productList = productRepository.findByStore(store);
        return productGroupLinkRepository.findByProduct(pageable, productList);
    }

    public Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXIST_PRODUCT));
    }


}
