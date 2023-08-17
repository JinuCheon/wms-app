package com.dope.wmsapp.outbound.feature;

import org.springframework.util.Assert;

class PackagingMaterial {
    private final String name;
    private final String code;
    private final PackagingMaterialDimension packagingMaterialDimension;
    private final Long weightInGrams;
    private final Long maxWeightInGrams;
    private final MaterialType materialType;

    public PackagingMaterial(final String name, final String code, final PackagingMaterialDimension packagingMaterialDimension, final Long weightInGrams, final Long maxWeightInGrams, final MaterialType materialType) {
        validateConstructor(name, code, packagingMaterialDimension, weightInGrams, maxWeightInGrams, materialType);
        this.name = name;
        this.code = code;
        this.packagingMaterialDimension = packagingMaterialDimension;
        this.weightInGrams = weightInGrams;
        this.maxWeightInGrams = maxWeightInGrams;
        this.materialType = materialType;
    }

    private static void validateConstructor(final String name, final String code, final PackagingMaterialDimension packagingMaterialDimension, final Long weightInGrams, final Long maxWeightInGrams, final MaterialType materialType) {
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(code, "code must not be empty");
        Assert.notNull(packagingMaterialDimension, "packagingMaterialDimension must not be null");
        Assert.notNull(weightInGrams, "weightInGrams must not be null");
        Assert.notNull(maxWeightInGrams, "maxWeightInGrams must not be null");
        Assert.notNull(materialType, "materialType must not be null");
    }
}
