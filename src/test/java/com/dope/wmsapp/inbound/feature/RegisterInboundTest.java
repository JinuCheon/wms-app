package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.product.domain.Product;
import com.dope.wmsapp.product.domain.ProductRepository;
import com.dope.wmsapp.product.fixture.ProductFixture;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class RegisterInboundTest extends ApiTest {

    private RegisterInbound registerInbound;
    @MockBean private ProductRepository productRepository;
    @Autowired private InboundRepository inboundRepository;

    @BeforeEach
    void setUpTest() {
        inboundRepository = new InboundRepository();
        registerInbound = new RegisterInbound(productRepository, inboundRepository);
    }

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        // mock 객체를 "Stubbing" 한다.
        // Stubbing: 객체의 행동을 조작하는 것
        Mockito.when(productRepository.getBy(Mockito.anyLong())).thenReturn(ProductFixture.aProduct().build());
        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;

        // 개별 입고에 대한 정보 레코드
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
                productNo,
                quantity,
                unitPrice,
                "description"
        );
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem); //입고가 여러개가 들어올 수 있군.

        // 입고 요청에 대한 정보 레코드. 동 시점에 들어오는 입고들의 정보를 가지고 있음.
        RegisterInbound.Request request = new RegisterInbound.Request(
                "title",
                "discription",
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems
        );

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/inbounds")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
        registerInbound.request(request);

        Assertions.assertThat(inboundRepository.findAll()).hasSize(1);
    }

}
