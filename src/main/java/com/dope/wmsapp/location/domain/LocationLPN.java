package com.dope.wmsapp.location.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location_lpn")
public class LocationLPN {
    @ManyToOne
    @JoinColumn(name = "location_location_no")
    private Location location;

}