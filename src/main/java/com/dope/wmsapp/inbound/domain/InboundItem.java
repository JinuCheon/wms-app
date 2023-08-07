package com.dope.wmsapp.inbound.domain;

import com.dope.wmsapp.product.domain.Product;
import org.springframework.util.Assert;

public class InboundItem {
    private final Product product;
    private final Long quantity;
    private final Long unitPrice;
    private final String description;

    public InboundItem(final Product product, final Long quantity, final Long unitPrice, final String description) {
        validateConstructor(product, quantity, unitPrice, description);
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    private static void validateConstructor(final Product product, final Long quantity, final Long unitPrice, final String description) {
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.notNull(quantity, "상품 수량은 필수입니다.");
        if (quantity < 1) {
            throw new IllegalArgumentException("상품 수량은 1개 이상이어야 합니다.");
        }
        Assert.notNull(unitPrice, "상품 단가는 필수입니다.");
        if (unitPrice < 0) {
            throw new IllegalArgumentException("상품 단가는 0원 이상이어야 합니다.");
        }
        Assert.hasText(description, "상품 설명은 필수입니다.");
    }
}
