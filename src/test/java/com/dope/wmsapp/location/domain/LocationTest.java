package com.dope.wmsapp.location.domain;

import com.dope.wmsapp.inbound.domain.LPN;
import com.dope.wmsapp.inbound.domain.LPNFixture;
import org.junit.jupiter.api.Test;

class LocationTest {

    private final com.dope.wmsapp.inbound.domain.LPNFixture LPNFixture = new LPNFixture();

    @Test
    void assignLPN() {
        final Location location = createLocation();

        final LPN lpn = LPNFixture.build();

        location.assignLPN(lpn);


    }

    private LPN createLpn() {
        return LPNFixture.build();
    }

    private Location createLocation() {
        final String locationBarcode = "A-1-1";
        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;
        final Location location = new Location(locationBarcode, storageType, usagePurpose);
        return location;
    }
}