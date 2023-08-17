package com.dope.wmsapp.location.domain;

import com.dope.wmsapp.inbound.domain.LPN;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "location_lpn")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class LocationLPN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_lpn_no")
    private Long locationLPNNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_no")
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lpn_no", nullable = false)
    private LPN lpn;
    @Column(name = "inventory_quantity")
    private Long inventoryQuantity;

    public LocationLPN(final Location location, final LPN lpn) {
        this.location = location;
        this.lpn = lpn;
        this.inventoryQuantity = 1L;
    }

    public void increaseQuantity() {
        this.inventoryQuantity++;
    }
}