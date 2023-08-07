package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundItem;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.product.domain.ProductRepository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

class RegisterInbound {
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

    public record Request(
            String title,
            String description,
            LocalDateTime orderRequestedAt,
            LocalDateTime estimatedArrivalAt,
            List<Request.Item> inboundItem) {

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
