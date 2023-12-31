package com.dope.wmsapp.outbound.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PackagingMaterialDimension {
    private Long innerWidthInMillimeters;
    private Long innerHeightInMillimeters;
    private Long innerLengthInMillimeters;
    private Long outerWidthInMillimeters;
    private Long outerHeightInMillimeters;
    private Long outerLengthInMillimeters;

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
