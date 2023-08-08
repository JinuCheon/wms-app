package com.dope.wmsapp.product.fixture;

import com.dope.wmsapp.product.domain.ProductSize;

public class ProductSizeFixture {

    private Long widthInMillimeters = 1L;
    private Long heightInMillimeters = 1L;
    private Long lengthInMillimeters = 1L;

    public static ProductSizeFixture aProductSize() {
        return new ProductSizeFixture();
    }

    public ProductSizeFixture withWidthInMillimeters(final Long widthInMillimeters) {
        this.widthInMillimeters = widthInMillimeters;
        return this;
    }

    public ProductSizeFixture withHeightInMillimeters(final Long heightInMillimeters) {
        this.heightInMillimeters = heightInMillimeters;
        return this;
    }

    public ProductSizeFixture withLengthInMillimeters(final Long lengthInMillimeters) {
        this.lengthInMillimeters = lengthInMillimeters;
        return this;
    }

    public ProductSize build() {
        return new ProductSize(
                widthInMillimeters,
                heightInMillimeters,
                lengthInMillimeters
        );
    }
}