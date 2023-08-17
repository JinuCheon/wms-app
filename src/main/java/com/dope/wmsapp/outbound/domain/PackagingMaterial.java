package com.dope.wmsapp.outbound.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "packaging_material")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PackagingMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "packaging_material_no")
    private Long packagingMaterialNo;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    @Embedded
    private PackagingMaterialDimension packagingMaterialDimension;
    @Column(nullable = false)
    private Long weightInGrams;
    @Column(nullable = false)
    private Long maxWeightInGrams;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

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
