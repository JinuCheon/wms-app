package com.dope.wmsapp.outbound.feature;

import com.dope.wmsapp.common.ApiTest;
import com.dope.wmsapp.common.Scenario;
import com.dope.wmsapp.outbound.domain.PackageMaterialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterPackagingMaterialTest extends ApiTest {

    @Autowired
    PackageMaterialRepository packageMaterialRepository;

    @Test
    void registerPackagingMaterial() {
        Scenario.registerPackageMaterial().request();
        assertThat(packageMaterialRepository.findAll()).hasSize(1);
    }

}
