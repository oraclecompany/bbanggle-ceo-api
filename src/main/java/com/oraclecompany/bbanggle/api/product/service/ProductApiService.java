package com.oraclecompany.bbanggle.api.product.service;

import com.oraclecompany.bbanggle.api.product.dto.ProductListResponseDto;
import com.oraclecompany.bbanggle.api.product.dto.ProductQuantityUpdateDto;
import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.entity.ProductGroupLink;
import com.oraclecompany.bbanggle.domain.product.service.ProductService;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.service.StoreService;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import com.oraclecompany.bbanggle.global.error.exception.InvalidValueException;
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
        Store store = storeService.findStoreById(storeId);
        Page<ProductGroupLink> productList = productService.selectProductList(pageable, store);
        return productList.stream()
                .map(ProductListResponseDto::of)
                .toList();
    }

    public void updateProductQuantity(Long productId, ProductQuantityUpdateDto productQuantityUpdateDto) {
        Product findProduct = productService.findProduct(productId);
        findProduct.modifyQuantity(productQuantityUpdateDto.getQuantity());
    }

    public void updateProductQuantityPlus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        findProduct.plusQuantity();
    }

    public void updateProductQuantityMinus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        if(findProduct.getQuantity() == 0) {
            throw new InvalidValueException(ErrorCode.INVALID_PRODUCT_QUANTITY);
        }
        findProduct.minusQuantity();
    }

    public void updateProductSellStatus(Long productId) {
        Product findProduct = productService.findProduct(productId);

        if(findProduct.getStatus() == SellStatus.SL) {
            findProduct.updateStatus(SellStatus.SO);
        } else {
            findProduct.updateStatus(SellStatus.SL);
        }
    }
}
