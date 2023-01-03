package com.oraclecompany.bbanggle.api.menu.service;

import com.oraclecompany.bbanggle.api.menu.dto.ProductListDto;
import com.oraclecompany.bbanggle.api.menu.dto.ProductQuantityUpdateDto;
import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import com.oraclecompany.bbanggle.domain.ceo.service.CeoService;
import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.service.ProductService;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import com.oraclecompany.bbanggle.global.error.exception.InvalidValueException;
import com.oraclecompany.bbanggle.global.resolver.ceoinfo.CeoInfoDto;
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
public class MenuApiService {

    private final ProductService productService;
    private final CeoService ceoService;

    public Page<ProductListDto.Response> getProductList(Pageable pageable, CeoInfoDto ceoInfoDto) {
        Ceo findCeo = ceoService.findCeoById(ceoInfoDto.getCeoId());
        Store store = findCeo.getStore();
        Page<Product> productList = productService.findProductList(pageable, store);
        List<ProductListDto.Response> productListDto = productList
                .map(ProductListDto.Response::of)
                .toList();

        return new PageImpl<>(productListDto, pageable, productList.getTotalElements());
    }

    @Transactional
    public void updateProductQuantity(Long productId, ProductQuantityUpdateDto productQuantityUpdateDto) {
        Product findProduct = productService.findProduct(productId);
        findProduct.modifyQuantity(productQuantityUpdateDto.getQuantity());
    }

    @Transactional
    public void updateProductQuantityPlus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        findProduct.plusQuantity();
    }

    @Transactional
    public void updateProductQuantityMinus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        findProduct.minusQuantity();
    }

    @Transactional
    public void updateProductSellStatus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        findProduct.toggleSellStatus();
    }
}
