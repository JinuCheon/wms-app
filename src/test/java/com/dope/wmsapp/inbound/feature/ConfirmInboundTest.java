package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.inbound.domain.InboundStatus;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ConfirmInboundTest extends ApiTest {

    @Autowired private ConfirmInbound confirmInbound;
    @Autowired private InboundRepository inboundRepository;

    @Test
    @DisplayName("입고를 승인한다.")
    void confirmInbound() {
        //given

        Scenario
                .registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request();
        //when
        final Inbound inbound = inboundRepository.getBy(1L);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

}
