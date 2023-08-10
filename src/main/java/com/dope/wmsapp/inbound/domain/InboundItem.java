package com.dope.wmsapp.inbound.domain;

import com.dope.wmsapp.product.domain.Product;
import com.google.common.annotations.VisibleForTesting;
import jakarta.el.CompositeELResolver;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "inbound_item")
@Comment("입고 품목")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InboundItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Comment("입고 품목 번호")
    @Column(name = "inbound_item_no")
    private Long inboundItemNo;
    @Comment("상품")
    @JoinColumn(name = "product_no", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Comment("상품 수량")
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @Comment("상품 단가")
    @Column(name = "unit_price", nullable = false)
    private Long unitPrice;
    @Comment("상품 설명")
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inbound_no", nullable = false)
    @Comment("입고 번호")
    private Inbound inbound;
    @OneToMany(mappedBy = "inboundItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LPN> lpnList = new ArrayList<>();

    public InboundItem(final Product product, final Long quantity, final Long unitPrice, final String description) {
        validateConstructor(product, quantity, unitPrice, description);
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    @VisibleForTesting
    public InboundItem(final Long inboundItemNo,
                       final Product product,
                       final Long quantity,
                       final Long unitPrice,
                       final String description) {
        this(product, quantity, unitPrice, description);
        this.inboundItemNo = inboundItemNo;
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

    //양방향
    public void assignedInbound(final Inbound inbound) {
        Assert.notNull(inbound, "입고는 필수입니다.");
        this.inbound = inbound;
    }

    public void registerLPN(final String lpnBarcode, final LocalDateTime expirationAt) {
        validateRegisterLPN(lpnBarcode, expirationAt);
        lpnList.add(newLPN(lpnBarcode, expirationAt));
    }

    private void validateRegisterLPN(final String lpnBarcode, final LocalDateTime expirationAt) {
        Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
        Assert.notNull(expirationAt, "유통기한은 필수입니다.");
    }

    private LPN newLPN(final String lpnBarcode, final LocalDateTime expirationAt) {
        return new LPN(
                lpnBarcode,
                expirationAt,
                this);
    }
}
