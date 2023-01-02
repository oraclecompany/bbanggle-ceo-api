package com.oraclecompany.bbanggle.api.menu.service;

import com.oraclecompany.bbanggle.api.menu.dto.ProductListDto;
import com.oraclecompany.bbanggle.api.menu.dto.ProductQuantityUpdateDto;
import com.oraclecompany.bbanggle.domain.ceo.entity.Ceo;
import com.oraclecompany.bbanggle.domain.ceo.service.CeoService;
import com.oraclecompany.bbanggle.domain.product.constant.SellStatus;
import com.oraclecompany.bbanggle.domain.product.entity.Product;
import com.oraclecompany.bbanggle.domain.product.service.ProductService;
import com.oraclecompany.bbanggle.domain.store.entity.Store;
import com.oraclecompany.bbanggle.domain.store.service.StoreService;
import com.oraclecompany.bbanggle.global.error.exception.ErrorCode;
import com.oraclecompany.bbanggle.global.error.exception.InvalidValueException;
import com.oraclecompany.bbanggle.global.resolver.ceoinfo.CeoInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MenuApiService {

    private final ProductService productService;
    private final CeoService ceoService;

    public List<ProductListDto.Response> getProductList(Pageable pageable, CeoInfoDto ceoInfoDto) {
        Ceo findCeo = ceoService.findCeoById(ceoInfoDto.getCeoId());
        Store store = findCeo.getStore();
        Page<Product> productList = productService.findProductList(pageable, store);

        return productList.stream()
                .map(ProductListDto.Response::of)
                .toList();
    }

    public void updateProductQuantity(Long productId, ProductQuantityUpdateDto productQuantityUpdateDto) {
        Product findProduct = productService.findProduct(productId);
        if(productQuantityUpdateDto.getQuantity() < 0 || productQuantityUpdateDto.getQuantity() > 99) {
            throw new InvalidValueException(ErrorCode.INVALID_PRODUCT_QUANTITY);
        }
        findProduct.modifyQuantity(productQuantityUpdateDto.getQuantity());
    }

    public void updateProductQuantityPlus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        if(findProduct.getQuantity() == 99) {
            throw new InvalidValueException(ErrorCode.INVALID_PRODUCT_QUANTITY);
        }
        findProduct.plusQuantity();
    }

    public void updateProductQuantityMinus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        if (findProduct.getQuantity() == 0) {
            findProduct.updateStatus(SellStatus.SL);
            throw new InvalidValueException(ErrorCode.INVALID_PRODUCT_QUANTITY);
        }
        findProduct.minusQuantity();
    }

    public void updateProductSellStatus(Long productId) {
        Product findProduct = productService.findProduct(productId);
        if (findProduct.getStatus() == SellStatus.SL) {
            findProduct.updateStatus(SellStatus.SO);
        } else {
            findProduct.updateStatus(SellStatus.SL);
        }
    }
}
