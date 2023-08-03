package com.dope.wmsapp.product.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.product.domain.ProductRepository;
import com.dope.wmsapp.product.feature.api.RegisterProductApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;


class RegisterProductTest extends ApiTest {

    @Autowired private RegisterProduct registerProduct;
    @Autowired private ProductRepository productRepository;

    @Test
    @DisplayName("상품을 등록한다.")
    void registerProduct() {
        //given
        new RegisterProductApi().request();

        //then
        assertThat(productRepository.findAll()).hasSize(1);
    }

}
