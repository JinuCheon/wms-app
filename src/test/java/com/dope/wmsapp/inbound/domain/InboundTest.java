package com.dope.wmsapp.inbound.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InboundTest {

    @Test
    @DisplayName("입고를 승인한다.")
    void confirmed() {
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        inbound.confirmed();

        assertThat(beforeStatus).isEqualTo(InboundStatus.REQUESTED);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

    @Test
    @DisplayName("입고를 승인한다. - 실패 입고의 상태가 요청 상태가 아닐 경우")
    void fail_invalid_status_confirmed() {
        final Inbound inbound = InboundFixture.anInbound().inboundStatus(InboundStatus.CONFIRMED).build();
        assertThatThrownBy(() -> {
            inbound.confirmed();
        }).isInstanceOf(IllegalStateException.class)
        .hasMessage("입고 요청 상태가 아닙니다.");

    }

    @Test
    @DisplayName("입고를 반려/거부하면 입고의 상태가 REJECT가 된다.")
    void reject() {
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        final String rejectionReason = "반려 사유";
        inbound.reject(rejectionReason);

        assertThat(beforeStatus).isEqualTo(InboundStatus.REQUESTED);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.REJECTED);
    }

    @Test
    @DisplayName("입고를 반려/거부한다 - 실패 입고의 상태가 요청 상태가 아닐 경우")
    void fail_invalid_status_rejected() {
        final Inbound inbound = InboundFixture.anInbound().inboundStatus(InboundStatus.CONFIRMED).build();

        final String rejectionReason = "반려 사유";
        assertThatThrownBy(() -> {
            inbound.reject(rejectionReason);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("입고 요청 상태가 아닙니다.");

    }

    @Test
    @DisplayName("LPN을 등록한다.")
    void registerLPN() {
        //given
        final Inbound inbound = InboundFixture.anInbound().inboundStatus(InboundStatus.CONFIRMED).build();
        final Long inboundItemNo = 1L;
        final String lpnBarcode = "LPN-0001";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1L);

        //when
        inbound.registerLPN(inboundItemNo, lpnBarcode, expirationAt);

        //then
        //TODO inboundItem.getLPN(lpnBarcode).isPresent()가 true인지  확인
    }
}