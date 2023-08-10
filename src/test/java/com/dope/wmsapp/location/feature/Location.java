package com.dope.wmsapp.location.feature;

import org.springframework.util.Assert;

class Location {
    private final String locationBarcode;
    private final StorageType storageType;
    private final UsagePurpose usagePurpose;
    private Long locationNo;

    public Location(final String locationBarcode, final StorageType storageType, final UsagePurpose usagePurpose) {
        validateConstructor(locationBarcode, storageType, usagePurpose);
        this.locationBarcode = locationBarcode;
        this.storageType = storageType;
        this.usagePurpose = usagePurpose;
    }

    private static void validateConstructor(final String locationBarcode, final StorageType storageType, final UsagePurpose usagePurpose) {
        Assert.notNull(locationBarcode, "로케이션 바코드는 필수입니다.");
        Assert.notNull(storageType, "로케이션 타입은 필수입니다.");
        Assert.notNull(usagePurpose, "보관 목적은 필수입니다.");
    }

    public void assignNo(final Long locationNo) {
        this.locationNo = locationNo;
    }

    public Long getLocationNo() {
        return locationNo;
    }
}
