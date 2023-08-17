package com.dope.wmsapp.outbound.feature;

import com.dope.wmsapp.outbound.domain.MaterialType;
import com.dope.wmsapp.outbound.domain.PackageMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterPackagingMaterialTest {

    RegisterPackageMaterial registerPackageMaterial;
    PackageMaterialRepository packageMaterialRepository;

    @BeforeEach
    void setUp() {
        packageMaterialRepository = new PackageMaterialRepository();
        registerPackageMaterial = new RegisterPackageMaterial(packageMaterialRepository);
    }

    @Test
    void registerPackagingMaterial() {
        final Long innerWidthInMillimeters = 1000L;
        final Long innerHeightInMillimeters = 1000L;
        final Long innerLengthInMillimeters = 1000L;
        final Long outerWidthInMillimeters = 1000L;
        final Long outerHeightInMillimeters = 1000L;
        final Long outerLengthInMillimeters = 1000L;
        final Long weightInGrams = 1000L;
        final Long maxWeightInGrams = 1000L;
        final MaterialType materialType = MaterialType.CORRUGATED_BOX;
        final RegisterPackageMaterial.Request request = new RegisterPackageMaterial.Request(
                "name",
                "code",
                innerWidthInMillimeters,
                innerHeightInMillimeters,
                innerLengthInMillimeters,
                outerWidthInMillimeters,
                outerHeightInMillimeters,
                outerLengthInMillimeters,
                weightInGrams,
                maxWeightInGrams,
                materialType
        );
        registerPackageMaterial.request(request);

        assertThat(packageMaterialRepository.finAll()).hasSize(1);
    }

}
