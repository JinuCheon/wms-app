package com.dope.wmsapp.location.domain;

import com.dope.wmsapp.inbound.domain.LPN;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Entity
@Getter
@Table(name = "location")
@Comment("로케이션")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_no", nullable = false)
    private Long locationNo;
    @Column(name = "location_barcode", nullable = false)
    private String locationBarcode;
    @Enumerated(EnumType.STRING)
    @Column(name = "storage_type", nullable = false)
    private StorageType storageType;
    @Enumerated(EnumType.STRING)
    @Column(name = "usage_purpose", nullable = false)
    private UsagePurpose usagePurpose;

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

    public void assignLPN(final LPN lpn) {

    }
}
