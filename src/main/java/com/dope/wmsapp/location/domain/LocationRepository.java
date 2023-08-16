package com.dope.wmsapp.location.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByLocationBarcode(String locationBarcode);
}
