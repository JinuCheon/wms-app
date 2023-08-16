package com.dope.wmsapp.inbound.domain;

import java.time.LocalDateTime;

public class LPNFixture {

    private String lpnBarcode = "LPN-1";
    private LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);
    private InboundItemFixture inboundItemFixture = InboundItemFixture.anInboundItem();

    public LPNFixture withLPNBarcode(final String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public LPNFixture withExpirationAt(final LocalDateTime expirationAt) {
        this.expirationAt = expirationAt;
        return this;
    }

    public LPNFixture withInboundItemFixture(final InboundItemFixture inboundItemFixture) {
        this.inboundItemFixture = inboundItemFixture;
        return this;
    }

    public LPN build() {
        return new LPN(lpnBarcode, expirationAt, inboundItemFixture.build());
    }
}