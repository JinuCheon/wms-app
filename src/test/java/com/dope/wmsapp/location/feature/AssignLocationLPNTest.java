package com.dope.wmsapp.location.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        public void request(final Request request) {

        }

        public record Request(String locationBarcode, String lpnBarcode) {
        }
    }
}
