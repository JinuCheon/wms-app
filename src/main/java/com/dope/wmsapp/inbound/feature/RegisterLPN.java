package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.inbound.domain.LPNRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class RegisterLPN {
    private final InboundRepository inboundRepository;
    private final LPNRepository lpnRepository;

    @Transactional
    @PostMapping("/inbound/inbound-items/{inboundItemNo}/lpn")
    public void request(@PathVariable final Long inboundItemNo,
                        @RequestBody @Valid final Request request) {
        lpnRepository.findByLPNBarcode(request.lpnBarcode).ifPresent(
                lpn -> {
                    throw new IllegalArgumentException("이미 존재하는 LPN 바코드입니다.");
                }
        );
        final Inbound inbound = inboundRepository.getInbound(inboundItemNo);

        inbound.registerLPN(inboundItemNo, request.lpnBarcode, request.expirationAt);
    }

    public record Request(
            @NotBlank(message = "LPN 바코드는 필수입니다.")
            String lpnBarcode,
            @NotNull(message = "유통기한은 필수입니다.")
            LocalDateTime expirationAt) {
    }
}
