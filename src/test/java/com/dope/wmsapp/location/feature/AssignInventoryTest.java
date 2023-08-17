package com.dope.wmsapp.location.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.location.domain.Inventory;
import com.dope.wmsapp.location.domain.Location;
import com.dope.wmsapp.location.domain.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AssignInventoryTest extends ApiTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    @DisplayName("로케이션에 LPN 할당")
    @Transactional
    void assignInventory() {
        // TODO setup 으로 옮기기
        Scenario.registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request()
                .registerLPN().request()
                .registerLocation().request();

        Scenario.assignInventory().request();

        final String locationBarcode = "A-1-1";
        final Location location = locationRepository.getByLocationBarcode(locationBarcode);
        final List<Inventory> inventories = location.getInventories();
        final Inventory inventory = inventories.get(0);
        assertThat(inventories).hasSize(1);
        assertThat(inventory.getInventoryQuantity()).isEqualTo(1L);
    }

}
