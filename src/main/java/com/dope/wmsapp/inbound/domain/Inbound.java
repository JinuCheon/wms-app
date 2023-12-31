package com.dope.wmsapp.inbound.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "inbound")
@Comment("입고")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Inbound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("입고 번호")
    private Long inboundNo;
    @Column(name = "title", nullable = false)
    @Comment("입고명")
    private String title;
    @Column(name = "description", nullable = false)
    @Comment("입고 설명")
    private String description;
    @Column(name = "order_requested_at", nullable = false)
    @Comment("입고 요청일")
    private LocalDateTime orderRequestedAt;
    @Column(name = "estimated_arrival_at", nullable = false)
    @Comment("입고 예정일")
    private LocalDateTime estimatedArrivalAt;
    @Column(name = "inbound_items", nullable = false)
    @Comment("입고 품목들")
    @OneToMany(mappedBy = "inbound", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InboundItem> inboundItems = new ArrayList<>();
    @Enumerated
    @Column(name = "status", nullable = false)
    @Comment("입고 상태")
    private InboundStatus status = InboundStatus.REQUESTED;
    @Column(name = "rejection_reason")
    @Comment("입고 거부 사유")
    private String rejectionReason;

    public Inbound(final String title,
                   final String description,
                   final LocalDateTime orderRequestedAt,
                   final LocalDateTime estimatedArrivalAt,
                   final List<InboundItem> inboundItems) {
        validateConstructor(title, description, orderRequestedAt, estimatedArrivalAt, inboundItems);
        this.title = title;
        this.description = description;
        this.orderRequestedAt = orderRequestedAt;
        this.estimatedArrivalAt = estimatedArrivalAt;
        for (final InboundItem inboundItem : inboundItems) {
            this.inboundItems.add(inboundItem);
            inboundItem.assignedInbound(this);
        }
        this.inboundItems = inboundItems;
    }

    public Inbound(final Long inboundNo,
                   final String title,
                   final String description,
                   final LocalDateTime orderRequestedAt,
                   final LocalDateTime estimatedArrivalAt,
                   final List<InboundItem> inboundItems,
                   final InboundStatus inboundStatus) {
        this(title, description, orderRequestedAt, estimatedArrivalAt, inboundItems);
        this.inboundNo = inboundNo;
        this.status = inboundStatus;
    }

    private static void validateConstructor(final String title, final String description, final LocalDateTime orderRequestedAt, final LocalDateTime estimatedArrivalAt, final List<InboundItem> inboundItems) {
        Assert.hasText(title, "입고 제목은 필수입니다.");
        Assert.hasText(description, "입고 설명은 필수입니다.");
        Assert.notNull(orderRequestedAt, "입고 요청일은 필수입니다.");
        Assert.notNull(estimatedArrivalAt, "입고 예정일은 필수입니다.");
        Assert.notEmpty(inboundItems, "입고 상품은 필수입니다.");
    }

    public void confirmed() {
        validateConfirmStatus();
        status = InboundStatus.CONFIRMED;
    }

    private void validateConfirmStatus() {
        if (status != InboundStatus.REQUESTED) {
            throw new IllegalStateException("입고 요청 상태가 아닙니다.");
        }
    }

    public void reject(final String rejectionReason) {
        this.rejectionReason = rejectionReason;
        validateRejectStatus(rejectionReason);
        status = InboundStatus.REJECTED;
    }

    private void validateRejectStatus(final String rejectionReason) {
        Assert.hasText(rejectionReason, "반려 사유는 필수입니다.");
        if (status != InboundStatus.REQUESTED) {
            throw new IllegalStateException("입고 요청 상태가 아닙니다.");
        }
    }

    public void registerLPN(final Long inboundItemNo, final String lpnBarcode, final LocalDateTime expirationAt) {
        validateRegisterLPN(inboundItemNo, lpnBarcode, expirationAt);
        final InboundItem inboundItem = getInboundItemBy(inboundItemNo);
        inboundItem.registerLPN(lpnBarcode, expirationAt);
    }

    private static void validateRegisterLPN(final Long inboundItemNo, final String lpnBarcode, final LocalDateTime expirationAt) {
        Assert.notNull(inboundItemNo, "입고 품목 번호는 필수입니다.");
        Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
        Assert.notNull(expirationAt, "유통기한은 필수입니다.");
    }

    private InboundItem getInboundItemBy(final Long inboundItemNo) {
        return inboundItems.stream()
                .filter(inboundItem -> inboundItem.getInboundItemNo().equals(inboundItemNo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 입고 품목이 없습니다."));
    }
}
