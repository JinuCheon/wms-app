package com.dope.wmsapp.location.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByLocationBarcode(String locationBarcode);

    default Location getByLocationBarcode(String locationBarcode) {
        return findByLocationBarcode(locationBarcode).orElseThrow(() -> new IllegalArgumentException("해당 로케이션 바코드가 존재하지 않습니다."));
    }
}
