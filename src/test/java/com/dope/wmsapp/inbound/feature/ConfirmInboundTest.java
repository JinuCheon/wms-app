package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundFixture;
import com.dope.wmsapp.inbound.domain.InboundRepository;
import com.dope.wmsapp.inbound.domain.InboundStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ConfirmInboundTest {

    private ConfirmInbound confirmInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        inboundRepository = Mockito.mock(InboundRepository.class);
        confirmInbound = new ConfirmInbound(inboundRepository);
    }
    @Test
    @DisplayName("입고를 승인한다.")
    void confirmInbound() {
        //given
        final Long inboundNo = 1L;
        final Inbound inbound = InboundFixture.anInbound().build();
        Mockito.when(inboundRepository.getBy(inboundNo))
                .thenReturn(inbound);
        //when
        confirmInbound.request(inboundNo);

        //then
        // TODO inbound상태가 승인으로 변경되었는지 확인
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

    private class ConfirmInbound {
        private final InboundRepository inboundRepository;

        public ConfirmInbound(final InboundRepository inboundRepository) {
            this.inboundRepository = inboundRepository;
        }

        public void request(final Long inboundNo) {
            final Inbound inbound = inboundRepository.getBy(inboundNo);

            inbound.confirmed();
        }

    }
}
