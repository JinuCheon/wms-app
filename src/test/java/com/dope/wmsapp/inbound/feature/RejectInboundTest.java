package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundFixture;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.inbound.domain.InboundStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class RejectInboundTest extends ApiTest {

    @Autowired RejectInbound rejectInbound;
    @Autowired InboundRepository inboundRepository;

    @Test
    @DisplayName("입고를 반려/거부한다.")
    void rejectInbound() {
        Scenario.
                registerProduct().request()
                .registerInbound().request()
                .rejectInbound().request();

        assertThat(inboundRepository.getBy(1L).getStatus()).isEqualTo(InboundStatus.REJECTED);
    }

}
