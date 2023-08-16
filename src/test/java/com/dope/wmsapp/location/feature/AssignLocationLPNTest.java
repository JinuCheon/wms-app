package com.dope.wmsapp.location.feature;

import com.dope.wmsapp.location.domain.Location;
import com.dope.wmsapp.location.domain.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class AssignLocationLPNTest {

    @Test
    @DisplayName("로케이션에 LPN 할당")
    void assignLocationLPN() {
        final AssignLocationLPN assignLocationLPN = new AssignLocationLPN();
        final String locationBarcode = "A-1-1";
        final String lpnBarcode = "LPN-1";
        final AssignLocationLPN.Request request = new AssignLocationLPN.Request(
                locationBarcode,
                lpnBarcode
        );

        assignLocationLPN.request(request);


    }

    private class AssignLocationLPN {
        private LocationRepository locationRepository;

        public void request(final Request request) {
            final Location location = locationRepository.getLocationByBarcode(request.locationBarcode);
        }

        public record Request(String locationBarcode, String lpnBarcode) {
            public Request {
                Assert.hasText(locationBarcode, "locationBarcode must not be empty");
                Assert.hasText(lpnBarcode, "lpnBarcode must not be empty");
            }
        }
    }
}
