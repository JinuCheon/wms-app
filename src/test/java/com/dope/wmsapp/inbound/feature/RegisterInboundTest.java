package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RegisterInboundTest extends ApiTest {

    @Autowired private InboundRepository inboundRepository;

    @Test
    @DisplayName("입고를 등록한다.")
    void registerInbound() {
        Scenario.registerProduct().request()
                .registerInbound().request();
        Assertions.assertThat(inboundRepository.findAll()).hasSize(1);
    }

}
