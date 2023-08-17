package com.dope.wmsapp.location.domain;

import com.dope.wmsapp.inbound.domain.LPN;
import com.dope.wmsapp.inbound.domain.LPNFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationTest {

    @Test
    void assignLPN() {
        final Location location = LocationFixture.aLocation().build();
        final LPN lpn = LPNFixture.anLPN().build();

        location.assignLPN(lpn);

        assertThat(location.getInventories()).hasSize(1);
        assertThat(location.getInventories().get(0).getInventoryQuantity()).isEqualTo(1L);
    }

    @Test
    void already_exist_assignLPN() {
        final Location location = LocationFixture.aLocation().build();
        final LPN lpn = LPNFixture.anLPN().build();

        location.assignLPN(lpn);
        location.assignLPN(lpn);

        assertThat(location.getInventories()).hasSize(1);
        assertThat(location.getInventories().get(0).getInventoryQuantity()).isEqualTo(2L);
    }

}