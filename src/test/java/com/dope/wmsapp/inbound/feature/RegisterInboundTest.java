package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.product.domain.Product;
import com.dope.wmsapp.product.domain.ProductRepository;
import com.dope.wmsapp.product.fixture.ProductFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class RegisterInboundTest {

    private RegisterInbound registerInbound;
    private ProductRepository productRepository;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        inboundRepository = new InboundRepository();
        registerInbound = new RegisterInbound(productRepository, inboundRepository);
    }

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        // mock 객체를 "Stubbing" 한다.
        // Stubbing: 객체의 행동을 조작하는 것
        final Product product = ProductFixture.aProduct().build();
        Mockito.when(productRepository.findById(Mockito.anyLong())).
                thenReturn(Optional.of(product));
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
        registerInbound.request(request);
    }

}
