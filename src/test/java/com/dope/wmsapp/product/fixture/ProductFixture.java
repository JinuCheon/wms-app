package com.dope.wmsapp.product.fixture;

import com.dope.wmsapp.product.domain.Category;
import com.dope.wmsapp.product.domain.Product;
import com.dope.wmsapp.product.domain.TemperatureZone;

public class ProductFixture {
    private String name = "name";
    private String code = "code";
    private String description = "description";
    private String category = "category";
    private String brand = "brand";
    private String origin = "origin";
    private Category electronics = Category.ELECTRONICS;
    private TemperatureZone roomTemperature = TemperatureZone.ROOM_TEMPERATURE;
    private long weightInGrams = 1000L;
    private ProductSizeFixture productSize = ProductSizeFixture.aProductSize();

    public static ProductFixture aProduct() {
        return new ProductFixture();
    }

    public ProductFixture name(final String name) {
        this.name = name;
        return this;
    }

    public ProductFixture code(final String code) {
        this.code = code;
        return this;
    }

    public ProductFixture description(final String description) {
        this.description = description;
        return this;
    }

    public ProductFixture category(final String category) {
        this.category = category;
        return this;
    }

    public ProductFixture brand(final String brand) {
        this.brand = brand;
        return this;
    }

    public ProductFixture origin(final String origin) {
        this.origin = origin;
        return this;
    }

    public ProductFixture electronics(final Category electronics) {
        this.electronics = electronics;
        return this;
    }

    public ProductFixture roomTemperature(final TemperatureZone roomTemperature) {
        this.roomTemperature = roomTemperature;
        return this;
    }

    public ProductFixture weightInGrams(final long weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public ProductFixture productSize(final ProductSizeFixture productSize) {
        this.productSize = productSize;
        return this;
    }

    public Product build() {
        return new Product(
                name,
                code,
                description,
                brand,
                category,
                origin,
                electronics,
                roomTemperature,
                weightInGrams,
                productSize.build()
        );
    }
}