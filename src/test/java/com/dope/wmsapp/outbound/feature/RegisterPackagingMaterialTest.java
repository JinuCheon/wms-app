package com.dope.wmsapp.outbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class RegisterPackagingMaterialTest {

    RegisterPackageMaterial registerPackageMaterial;
    private PackageMaterialRepository packageMaterialRepository;

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

//        assertThat(packageMaterialRepository.finAll()).hasSize(1);
    }

    private static class PackagingMaterial {
        private final String name;
        private final String code;
        private final RegisterPackageMaterial.Request.PackagingMaterialDimension packagingMaterialDimension;
        private final Long weightInGrams;
        private final Long maxWeightInGrams;
        private final MaterialType materialType;

        public PackagingMaterial(final String name, final String code, final RegisterPackageMaterial.Request.PackagingMaterialDimension packagingMaterialDimension, final Long weightInGrams, final Long maxWeightInGrams, final MaterialType materialType) {
            validateConstructor(name, code, packagingMaterialDimension, weightInGrams, maxWeightInGrams, materialType);
            this.name = name;
            this.code = code;
            this.packagingMaterialDimension = packagingMaterialDimension;
            this.weightInGrams = weightInGrams;
            this.maxWeightInGrams = maxWeightInGrams;
            this.materialType = materialType;
        }

        private static void validateConstructor(final String name, final String code, final RegisterPackageMaterial.Request.PackagingMaterialDimension packagingMaterialDimension, final Long weightInGrams, final Long maxWeightInGrams, final MaterialType materialType) {
            Assert.hasText(name, "name must not be empty");
            Assert.hasText(code, "code must not be empty");
            Assert.notNull(packagingMaterialDimension, "packagingMaterialDimension must not be null");
            Assert.notNull(weightInGrams, "weightInGrams must not be null");
            Assert.notNull(maxWeightInGrams, "maxWeightInGrams must not be null");
            Assert.notNull(materialType, "materialType must not be null");
        }
    }

    private class RegisterPackageMaterial {
        public void request(final Request request) {
            final PackagingMaterial packagingMaterial = request.toDomain();
        }

        record Request(String name,
                       String code,
                       Long innerWidthInMillimeters,
                       Long innerHeightInMillimeters,
                       Long innerLengthInMillimeters,
                       Long outerWidthInMillimeters,
                       Long outerHeightInMillimeters,
                       Long outerLengthInMillimeters,
                       Long weightInGrams,
                       Long maxWeightInGrams,
                       MaterialType materialType) {
            Request {
                Assert.hasText(name, "name must not be empty");
                Assert.hasText(code, "code must not be empty");
                Assert.notNull(innerWidthInMillimeters, "innerWidthInMillimeters must not be null");
                if (innerWidthInMillimeters < 1) {
                    throw new IllegalArgumentException("innerWidthInMillimeters must be greater than 1");
                }
                Assert.notNull(innerHeightInMillimeters, "innerHeightInMillimeters must not be null");
                if (innerHeightInMillimeters < 1) {
                    throw new IllegalArgumentException("innerHeightInMillimeters must be greater than 1");
                }
                Assert.notNull(innerLengthInMillimeters, "innerLengthInMillimeters must not be null");
                if (innerLengthInMillimeters < 1) {
                    throw new IllegalArgumentException("innerLengthInMillimeters must be greater than 1");
                }
                Assert.notNull(outerWidthInMillimeters, "outerWidthInMillimeters must not be null");
                if (outerWidthInMillimeters < 1) {
                    throw new IllegalArgumentException("outerWidthInMillimeters must be greater than 1");
                }
                Assert.notNull(outerHeightInMillimeters, "outerHeightInMillimeters must not be null");
                if (outerHeightInMillimeters < 1) {
                    throw new IllegalArgumentException("outerHeightInMillimeters must be greater than 1");
                }
                Assert.notNull(outerLengthInMillimeters, "outerLengthInMillimeters must not be null");
                if (outerLengthInMillimeters < 1) {
                    throw new IllegalArgumentException("outerLengthInMillimeters must be greater than 1");
                }
                Assert.notNull(weightInGrams, "weightInGrams must not be null");
                if (weightInGrams < 1) {
                    throw new IllegalArgumentException("weightInGrams must be greater than 1");
                }
                Assert.notNull(maxWeightInGrams, "maxWeightInGrams must not be null");
                if (maxWeightInGrams < 1) {
                    throw new IllegalArgumentException("maxWeightInGrams must be greater than 1");
                }
                Assert.notNull(materialType, "corrugatedBox must not be null");
            }

            public PackagingMaterial toDomain() {
                return new PackagingMaterial(
                        name,
                        code,
                        new PackagingMaterialDimension(
                                innerWidthInMillimeters,
                                innerHeightInMillimeters,
                                innerLengthInMillimeters,
                                outerWidthInMillimeters,
                                outerHeightInMillimeters,
                                outerLengthInMillimeters
                        ),
                        weightInGrams,
                        maxWeightInGrams,
                        materialType
                );
            }

            private class PackagingMaterialDimension {
                private final Long innerWidthInMillimeters;
                private final Long innerHeightInMillimeters;
                private final Long innerLengthInMillimeters;
                private final Long outerWidthInMillimeters;
                private final Long outerHeightInMillimeters;
                private final Long outerLengthInMillimeters;

                public PackagingMaterialDimension(final Long innerWidthInMillimeters, final Long innerHeightInMillimeters, final Long innerLengthInMillimeters, final Long outerWidthInMillimeters, final Long outerHeightInMillimeters, final Long outerLengthInMillimeters) {
                    validateConstructor(innerWidthInMillimeters, innerHeightInMillimeters, innerLengthInMillimeters, outerWidthInMillimeters, outerHeightInMillimeters, outerLengthInMillimeters);
                    this.innerWidthInMillimeters = innerWidthInMillimeters;
                    this.innerHeightInMillimeters = innerHeightInMillimeters;
                    this.innerLengthInMillimeters = innerLengthInMillimeters;
                    this.outerWidthInMillimeters = outerWidthInMillimeters;
                    this.outerHeightInMillimeters = outerHeightInMillimeters;
                    this.outerLengthInMillimeters = outerLengthInMillimeters;
                }

                private static void validateConstructor(final Long innerWidthInMillimeters, final Long innerHeightInMillimeters, final Long innerLengthInMillimeters, final Long outerWidthInMillimeters, final Long outerHeightInMillimeters, final Long outerLengthInMillimeters) {
                    Assert.notNull(innerWidthInMillimeters, "innerWidthInMillimeters must not be null");
                    if (innerWidthInMillimeters < 1) {
                        throw new IllegalArgumentException("innerWidthInMillimeters must be greater than 1");
                    }
                    Assert.notNull(innerHeightInMillimeters, "innerHeightInMillimeters must not be null");
                    if (innerHeightInMillimeters < 1) {
                        throw new IllegalArgumentException("innerHeightInMillimeters must be greater than 1");
                    }
                    Assert.notNull(innerLengthInMillimeters, "innerLengthInMillimeters must not be null");
                    if (innerLengthInMillimeters < 1) {
                        throw new IllegalArgumentException("innerLengthInMillimeters must be greater than 1");
                    }
                    Assert.notNull(outerWidthInMillimeters, "outerWidthInMillimeters must not be null");
                    if (outerWidthInMillimeters < 1) {
                        throw new IllegalArgumentException("outerWidthInMillimeters must be greater than 1");
                    }
                    Assert.notNull(outerHeightInMillimeters, "outerHeightInMillimeters must not be null");
                    if (outerHeightInMillimeters < 1) {
                        throw new IllegalArgumentException("outerHeightInMillimeters must be greater than 1");
                    }
                    Assert.notNull(outerLengthInMillimeters, "outerLengthInMillimeters must not be null");
                    if (outerLengthInMillimeters < 1) {
                        throw new IllegalArgumentException("outerLengthInMillimeters must be greater than 1");
                    }
                }
            }
        }
    }

    enum MaterialType {
        CORRUGATED_BOX("골판지 상자");
        private final String description;

        MaterialType(final String description) {
            this.description = description;
        }
    }

    private class PackageMaterialRepository {
    }
}
