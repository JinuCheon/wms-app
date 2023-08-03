package com.dope.wmsapp.product.feature;

import org.springframework.util.Assert;

public class Product {
    private Long id;
    private final String name;
    private final String code;
    private final String description;
    private final String brand;
    private final String maker;
    private final String origin;
    private final Category category;
    private final TemperatureZone temperatureZone;
    private final Long weightInGrams;
    private final ProductSize productSize;

    public Product(final String name,
                   final String code,
                   final String description,
                   final String brand,
                   final String maker,
                   final String origin,
                   final Category category,
                   final TemperatureZone temperatureZone,
                   final Long weightInGrams,
                   final ProductSize productSize) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.brand = brand;
        this.maker = maker;
        this.origin = origin;
        this.category = category;
        this.temperatureZone = temperatureZone;
        this.weightInGrams = weightInGrams;
        this.productSize = productSize;
        validateConstructor(name, code, description, brand, maker, origin, category, temperatureZone, weightInGrams, productSize);

    }

    private static void validateConstructor(final String name,
                                            final String code,
                                            final String description,
                                            final String brand,
                                            final String maker,
                                            final String origin,
                                            final Category category,
                                            final TemperatureZone temperatureZone,
                                            final Long weightInGrams,
                                            final ProductSize productSize) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.hasText(code, "상품코드는 필수입니다.");
        Assert.hasText(description, "상품설명은 필수입니다.");
        Assert.hasText(brand, "브랜드는 필수입니다.");
        Assert.hasText(maker, "제조사는 필수입니다.");
        Assert.hasText(origin, "원산지는 필수입니다.");
        Assert.notNull(category, "카테고리는 필수입니다.");
        Assert.notNull(temperatureZone, "온도대는 필수입니다.");
        Assert.notNull(weightInGrams, "무게는 필수입니다.");
        if (0 > weightInGrams) {
            throw new IllegalArgumentException("무게는 0보다 커야합니다.");
        }
        Assert.notNull(productSize, "상품크기는 필수입니다.");
    }

    public void assignId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
