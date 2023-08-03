package com.dope.wmsapp.product.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
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
        // Scenario.registerProduct().request()
        //   .registerProduct().request();
        // 내부에서 scenario를 return하도록 만들어서, 여러번 호출이 가능하다.
        Scenario.registerProduct().request();

        //then
        assertThat(productRepository.findAll()).hasSize(1);
    }

}
