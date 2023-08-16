package com.dope.wmsapp.location.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.location.domain.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterLocationTest extends ApiTest {

    @Autowired
    LocationRepository locationRepository;

    @Test
    @DisplayName("로케이션을 등록한다.")
    void registerLocation() {
        Scenario.registerLocation().request();

        assertThat(locationRepository.findAll()).hasSize(1);
    }

}
