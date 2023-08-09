package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundFixture;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.inbound.domain.InboundStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class RejectInboundTest {

    RejectInbound rejectInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        inboundRepository = Mockito.mock(InboundRepository.class);
        rejectInbound = new RejectInbound(inboundRepository);
    }
    @Test
    @DisplayName("입고를 반려/거부한다.")
    void rejectInbound() {
        final Long inboundNo = 1L;
        final Inbound inbound = InboundFixture.anInbound().build();
        Mockito.when(inboundRepository.getBy(inboundNo))
                .thenReturn(inbound);

        final String rejectionReason = "반려 사유";
        final RejectInbound.Request request = new RejectInbound.Request(rejectionReason);
        rejectInbound.request(inboundNo, request);

        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.REJECTED);
    }

}
