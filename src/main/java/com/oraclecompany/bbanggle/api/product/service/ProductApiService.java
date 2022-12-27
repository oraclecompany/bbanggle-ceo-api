package com.oraclecompany.bbanggle.api.product.service;

import com.oraclecompany.bbanggle.api.product.dto.ProductListResponseDto;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductGroupLink;
import com.oraclecompany.bbanggle.domain.product.entity.ProductTimetable;
import com.oraclecompany.bbanggle.domain.product.repository.ProductGroupLinkRepository;
import com.oraclecompany.bbanggle.domain.product.service.ProductService;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductApiService {

    private final ProductService productService;
    private final StoreService storeService;

    public List<ProductListResponseDto> selectProductList(Pageable pageable, Long storeId) {
        //TODO 여기에 스토어 서비스 만들기
        Store store = storeService.selectStore(storeId);

        Page<ProductGroupLink> productList = productService.selectProductList(pageable, store);

        return productList.stream()
                .map(ProductListResponseDto::of)
                .toList();
    }
}