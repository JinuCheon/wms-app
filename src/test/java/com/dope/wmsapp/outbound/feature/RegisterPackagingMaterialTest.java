package com.dope.wmsapp.outbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegisterPackagingMaterialTest {

    RegisterPackageMaterial registerPackageMaterial;

    @BeforeEach
    void setUp() {
        registerPackageMaterial = new RegisterPackageMaterial();
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
                MaterialType.CORRUGATED_BOX
        );
        registerPackageMaterial.request(request);
    }

    private class RegisterPackageMaterial {
        public void request(final Request request) {

        }

        public class Request {
        }
    }

    enum MaterialType {
        CORRUGATED_BOX("골판지 상자");
        private final String description;

        MaterialType(final String description) {
            this.description = description;
        }
    }
}
