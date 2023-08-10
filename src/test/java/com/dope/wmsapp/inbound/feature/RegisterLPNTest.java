package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

class RegisterLPNTest extends ApiTest {

    @Autowired RegisterLPN registerLPN;
    @Autowired InboundRepository inboundRepository;

    @Test
    @DisplayName("LPN을 등록한다.")
    void registerLPN() {
        Scenario.registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request();

        final Long inboundItemNo = 1L;
        final String lpnBarcode = "LPN-0001";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1L);
        RegisterLPN.Request request = new RegisterLPN.Request(
                inboundItemNo,
                lpnBarcode,
                expirationAt
        );
        registerLPN.request(request);
    }

}
