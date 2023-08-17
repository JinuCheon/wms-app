package com.dope.wmsapp.location.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.location.domain.Location;
import com.dope.wmsapp.location.domain.LocationLPN;
import com.dope.wmsapp.location.domain.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AssignLocationLPNTest extends ApiTest {

    @Autowired
    private AssignLocationLPN assignLocationLPN;
    @Autowired
    private LocationRepository locationRepository;

    @Test
    @DisplayName("로케이션에 LPN 할당")
    @Transactional
    void assignLocationLPN() {
        // TODO setup 으로 옮기기
        Scenario.registerProduct().request()
                .registerInbound().request()
                .confirmInbound().request()
                .registerLPN().request()
                .registerLocation().request();

        final String locationBarcode = "A-1-1";
        final String lpnBarcode = "LPN-1";
        final AssignLocationLPN.Request request = new AssignLocationLPN.Request(
                locationBarcode,
                lpnBarcode
        );

        assignLocationLPN.request(request);

        final Location location = locationRepository.getByLocationBarcode(locationBarcode);
        final List<LocationLPN> locationLPNList = location.getLocationLPNList();
        final LocationLPN locationLPN = locationLPNList.get(0);
        assertThat(locationLPNList).hasSize(1);
        assertThat(locationLPN.getInventoryQuantity()).isEqualTo(1L);
    }
}
