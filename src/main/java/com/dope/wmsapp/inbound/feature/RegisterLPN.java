package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Component
class RegisterLPN {
    private InboundRepository inboundRepository;

    public RegisterLPN(final InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    @Transactional
    public void request(final Request request) {
        final Inbound inbound = inboundRepository.getInbound(request.inboundItemNo);

        inbound.registerLPN(request.inboundItemNo, request.lpnBarcode, request.expirationAt);
    }

    public record Request(Long inboundItemNo,
                          String lpnBarcode,
                          LocalDateTime expirationAt) {
        public Request {
            Assert.notNull(inboundItemNo, "LPN을 등록할 입고 상품 번호는 필수입니다.");
            Assert.notNull(lpnBarcode, "LPN 바코드는 필수입니다.");
            Assert.notNull(expirationAt, "유통기한은 필수입니다.");
        }
    }
}
