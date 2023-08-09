package com.dope.wmsapp.inbound.domain;

public enum InboundStatus {
    REQUESTED("요청됨"),
    CONFIRMED("승인됨"),
    REJECTED("거절됨");

    private final String description;

    InboundStatus(final String description) {
        this.description = description;
    }

}
