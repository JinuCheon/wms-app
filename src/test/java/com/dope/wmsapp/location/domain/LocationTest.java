package com.dope.wmsapp.location.domain;

import com.dope.wmsapp.inbound.domain.InboundItemFixture;
import com.dope.wmsapp.inbound.domain.LPN;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class LocationTest {

    @Test
    void assignLPN() {
        final Location location = createLocation();

        final LPN lpn = createLpn();

        location.assignLPN(lpn);


    }

    private LPN createLpn() {
        final String lpnBarcode = "LPN-1";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);
        final LPN lpn = new LPN(lpnBarcode, expirationAt, InboundItemFixture.anInboundItem().build());
        return lpn;
    }

    private Location createLocation() {
        final String locationBarcode = "A-1-1";
        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;
        final Location location = new Location(locationBarcode, storageType, usagePurpose);
        return location;
    }
}