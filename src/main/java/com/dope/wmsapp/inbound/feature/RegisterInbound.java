package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundItem;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.product.domain.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
class RegisterInbound {
    private final ProductRepository productRepository;
    private final InboundRepository inboundRepository;

    public RegisterInbound(final ProductRepository productRepository, final InboundRepository inboundRepository) {
        this.productRepository = productRepository;
        this.inboundRepository = inboundRepository;
    }

    @PostMapping("inbounds")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody @Valid final Request request) {
        // TODO 요청을 도메인으로 변경해서 도메인을 저장한다.
        final Inbound inbound = createInbound(request);
        inboundRepository.save(inbound);
    }

    private Inbound createInbound(final Request request) {
        return new Inbound(
                request.title,
                request.description,
                request.orderRequestedAt,
                request.estimatedArrivalAt,
                mapToInboundItems(request)
        );
    }

    private List<InboundItem> mapToInboundItems(final Request request) {
        return request.inboundItem.stream()
                .map(item -> newInboundItem(item))
                .toList();
    }

    private InboundItem newInboundItem(final Request.Item item) {
        return new InboundItem(
                productRepository.getBy(item.productNo),
                item.quantity,
                item.unitPrice,
                item.description
        );
    }

    public record Request(
            @NotBlank(message = "입고 제목은 필수입니다.")
            String title,
            @NotBlank(message = "입고 설명은 필수입니다.")
            String description,
            @NotNull(message = "입고 요청일은 필수입니다.")
            LocalDateTime orderRequestedAt,
            @NotNull(message = "입고 예정일은 필수입니다.")
            LocalDateTime estimatedArrivalAt,
            @NotEmpty(message = "입고 상품은 필수입니다.")
            List<Request.Item> inboundItem) {
            public record Item(
                    @NotNull(message = "상품 번호는 필수입니다.")
                    Long productNo,
                    @NotNull(message = "상품 수량은 필수입니다.")
                    Long quantity,
                    @NotNull(message = "상품 단가는 필수입니다.")
                    @Min(value = 0, message = "상품 단가는 0원 이상이어야 합니다.")
                    Long unitPrice,
                    @NotBlank(message = "상품 설명은 필수입니다.")
                    @NotNull(message = "상품 설명은 필수입니다.")
                    String description) {
            }
    }

}
