package com.dope.wmsapp.location.domain;

import com.dope.wmsapp.inbound.domain.LPN;
import com.dope.wmsapp.inbound.domain.LPNFixture;
import org.junit.jupiter.api.Test;

class LocationTest {

    private final com.dope.wmsapp.inbound.domain.LPNFixture LPNFixture = new LPNFixture();
    private final LocationFixture locationFixture = new LocationFixture();

    @Test
    void assignLPN() {
        final Location location = locationFixture.build();
        final LPN lpn = LPNFixture.build();

        location.assignLPN(lpn);
    }

    private LPN createLpn() {
        return LPNFixture.build();
    }

    private Location createLocation() {
        return locationFixture.build();
    }
}