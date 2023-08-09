package com.dope.wmsapp.inbound.domain;

import com.dope.wmsapp.product.fixture.ProductFixture;

public class InboundItemFixture {

    private Long inboundItemNo = 1L;
    private ProductFixture product = ProductFixture.aProduct();
    private Long quantity = 1L;
    private Long unitPrice = 1500L;
    private String description = "description";

    public static InboundItemFixture anInboundItem() {
        return new InboundItemFixture();
    }

    public static InboundItem createInboundItem() {
        return anInboundItem().build();
    }

    public InboundItemFixture withInboundItemNo(final Long inboundItemNo) {
        this.inboundItemNo = inboundItemNo;
        return this;
    }

    public InboundItemFixture withProduct(final ProductFixture product) {
        this.product = product;
        return this;
    }

    public InboundItemFixture withQuantity(final Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public InboundItemFixture withUnitPrice(final Long unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public InboundItemFixture withDescription(final String description) {
        this.description = description;
        return this;
    }

    public InboundItem build() {
        return new InboundItem(
                inboundItemNo,
                ProductFixture.aProduct().build(),
                quantity,
                unitPrice,
                description
        );
    }
}