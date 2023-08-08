package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConfirmInboundTest {


    private ConfirmInbound confirmInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        confirmInbound = new ConfirmInbound();
    }
    @Test
    @DisplayName("입고를 승인한다.")
    void confirmInbound() {
        //given
        final Long inboundNo = 1L;

        //when
        confirmInbound.request(inboundNo);

        //then
        // TODO inbound상태가 승인으로 변경되었는지 확인
//        assertThat(inboundRepository.findById(inboundNo).get().getStatus()).isEqualTo("승인");
    }

    private class ConfirmInbound {
        public void request(final Long inboundNo) {
            final Inbound inbound = inboundRepository.findById(inboundNo).orElseThrow();

            inbound.confirmed();
        }
    }
}
