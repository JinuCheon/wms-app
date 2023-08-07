package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.product.domain.Category;
import com.dope.wmsapp.product.domain.Product;
import com.dope.wmsapp.product.domain.ProductRepository;
import com.dope.wmsapp.product.domain.ProductSize;
import com.dope.wmsapp.product.domain.TemperatureZone;
import groovyjarjarantlr4.v4.gui.TreeViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class RegisterInboundTest {

    private RegisterInbound registerInbound;
    private ProductRepository productRepository;
    private RegisterInbound.InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        inboundRepository = new RegisterInbound.InboundRepository();
        registerInbound = new RegisterInbound(productRepository, inboundRepository);
    }

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        // mock 객체를 "Stubbing" 한다.
        // Stubbing: 객체의 행동을 조작하는 것
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Product(
                "name",
                "code",
                "description",
                "brand",
                "category",
                "origin",
                Category.ELECTRONICS,
                TemperatureZone.ROOM_TEMPERATURE,
                1000L,
                 new ProductSize(
                            1L,
                            1L,
                            1L
                    )
                 )
        ));
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

    private static class RegisterInbound {
        private final ProductRepository productRepository;
        private final InboundRepository inboundRepository;

        public RegisterInbound(final ProductRepository productRepository, final InboundRepository inboundRepository) {
            this.productRepository = productRepository;
            this.inboundRepository = inboundRepository;
        }

        public void request(final Request request) {
            // TODO 요청을 도메인으로 변경해서 도메인을 저장한다.

            // 음. 새로운 InboundItem 객체를 한 번 더 생성하는 이유가 있나..?
            final List<InboundItem> inboundItems = request.inboundItem.stream()
                    .map(item -> new InboundItem(
                            productRepository.findById(item.productNo).orElseThrow(),
                            item.quantity,
                            item.unitPrice,
                            item.description
                    ))
                    .toList();
            // '입고' 도메인 객체 생성. 즉, 입고는 개별 아이템(InboundItem)을 여러개 가지고 있다.
            final Inbound inbound = new Inbound(
                    request.title,
                    request.description,
                    request.orderRequestedAt,
                    request.estimatedArrivalAt,
                    inboundItems
            );
            inboundRepository.save(inbound);
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

        private class InboundItem {
            private final Product product;
            private final Long quantity;
            private final Long unitPrice;
            private final String description;

            public InboundItem(final Product product, final Long quantity, final Long unitPrice, final String description) {
                validateConstructor(product, quantity, unitPrice, description);
                this.product = product;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
                this.description = description;
            }

            private static void validateConstructor(final Product product, final Long quantity, final Long unitPrice, final String description) {
                Assert.notNull(product, "상품은 필수입니다.");
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

        private class Inbound {
            private final String title;
            private final String description;
            private final LocalDateTime orderRequestedAt;
            private final LocalDateTime estimatedArrivalAt;
            private final List<InboundItem> inboundItems;
            private Long id;

            public Inbound(final String title, final String description, final LocalDateTime orderRequestedAt, final LocalDateTime estimatedArrivalAt, final List<InboundItem> inboundItems) {
                validateConstructor(title, description, orderRequestedAt, estimatedArrivalAt, inboundItems);
                this.title = title;
                this.description = description;
                this.orderRequestedAt = orderRequestedAt;
                this.estimatedArrivalAt = estimatedArrivalAt;
                this.inboundItems = inboundItems;
            }

            private static void validateConstructor(final String title, final String description, final LocalDateTime orderRequestedAt, final LocalDateTime estimatedArrivalAt, final List<InboundItem> inboundItems) {
                Assert.hasText(title, "입고 제목은 필수입니다.");
                Assert.hasText(description, "입고 설명은 필수입니다.");
                Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다.");
                Assert.notNull(estimatedArrivalAt, "입고 예정일은 필수입니다.");
                Assert.notEmpty(inboundItems, "입고 상품은 필수입니다.");
            }

            public void assignedId(final Long id) {
                this.id = id;
            }

            public Long getId() {
                return this.id;
            }
        }
        public static class InboundRepository {
            private final Map<Long, Inbound> inbounds = new HashMap<>();
            private Long sequence = 1L;
            public void save(final Inbound inbound) {
                inbound.assignedId(sequence++);
                inbounds.put(inbound.getId(), inbound);
            }
        }
    }

}
