package com.dope.wmsapp.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LPNRepository extends JpaRepository<LPN, Long> {
    @Query("select l from LPN l where l.lpnBarcode = :lpnBarcode")
    Optional<LPN> findByLPNBarcode(String lpnBarcode);

    default LPN getLPNByLPNBarcode(String lpnBarcode) {
        return findByLPNBarcode(lpnBarcode).orElseThrow(() -> new IllegalArgumentException("LPN이 존재하지 않습니다."));
    }
}
