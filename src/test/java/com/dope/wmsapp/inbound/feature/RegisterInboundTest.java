package com.dope.wmsapp.inbound.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

class RegisterInboundTest {

    private RegisterInbound registerInbound;

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
                productNo,
                quantity,
                unitPrice,
                "description"
        );
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem);
        RegisterInbound.Request request = new RegisterInbound.Request(
                "title",
                "discription",
                orderRequestedAt,
                estimatedArrivalAt,
                inboundItems
        );
        registerInbound.request(request);
    }

    private static class RegisterInbound {
        public void request(final Request request) {
        }

        public record Request (
                String title,
                String description,
                LocalDateTime orderRequestedAt,
                LocalDateTime estimatedArrivalAt,
                List<Item> inboundItem) {

            public Request {
                Assert.hasText(title, "입고 제목은 필수입니다.");
                Assert.hasText(description, "입고 설명은 필수입니다.");
                Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다.");
                Assert.notNull(estimatedArrivalAt, "입고 예정일은 필수입니다.");
                Assert.notEmpty(inboundItem, "입고 상품은 필수입니다.");
            }

            public record Item(
                 Long productNo,
                 Long quantity,
                 Long unitPrice,
                 String description) {

                public Item {
                    Assert.notNull(productNo, "상품 번호는 필수입니다.");
                    Assert.notNull(quantity, "상품 수량은 필수입니다.");
                    if (quantity < 1) {
                        throw new IllegalArgumentException("상품 수량은 1개 이상이어야 합니다.");
                    }
                    Assert.notNull(unitPrice, "상품 단가는 필수입니다.");
                    if (unitPrice < 0) {
                        throw new IllegalArgumentException("상품 단가는 0원 이상이어야 합니다.");
                    }
                    Assert.hasText(description, "상품 설명은 필수입니다.");
                }
            }
        }
    }
}
