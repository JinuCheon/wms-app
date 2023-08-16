package com.dope.wmsapp.location.domain;

import com.dope.wmsapp.inbound.domain.LPN;
import lombok.Getter;

@Getter
public class LocationLPN {
    private Location location;
    private LPN lpn;
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