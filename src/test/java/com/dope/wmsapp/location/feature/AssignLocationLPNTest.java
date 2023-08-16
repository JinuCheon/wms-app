package com.dope.wmsapp.location.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AssignLocationLPNTest {

    @Test
    @DisplayName("로케이션에 LPN 할당")
    void assignLocationLPN() {
        final AssignLocationLPN assignLocationLPN = new AssignLocationLPN();
        final AssignLocationLPN.Request request;
        assignLocationLPN.request(request);
    }

    private class AssignLocationLPN {
        public void request(final Request request) {

        }

        public record Request() {
        }
    }
}
