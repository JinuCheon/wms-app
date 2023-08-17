package com.dope.wmsapp.outbound.feature;

import com.dope.wmsapp.outbound.domain.MaterialType;
import com.dope.wmsapp.outbound.domain.PackageMaterialRepository;
import com.dope.wmsapp.outbound.domain.PackagingMaterial;
import com.dope.wmsapp.outbound.domain.PackagingMaterialDimension;
import org.springframework.util.Assert;

class RegisterPackageMaterial {
    private PackageMaterialRepository packagingMaterialRepository;

    public RegisterPackageMaterial(final PackageMaterialRepository packagingMaterialRepository) {
        this.packagingMaterialRepository = packagingMaterialRepository;
    }

    public void request(final Request request) {
        final PackagingMaterial packagingMaterial = request.toDomain();
        packagingMaterialRepository.save(packagingMaterial);
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

    }


}
