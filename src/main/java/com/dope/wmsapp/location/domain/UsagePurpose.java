package com.dope.wmsapp.location.domain;

public enum UsagePurpose {
    MOVE("이동 목적");
    private final String description;

    UsagePurpose(String description) {
        this.description = description;
    }
}
