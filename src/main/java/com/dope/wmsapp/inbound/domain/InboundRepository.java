package com.dope.wmsapp.inbound.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InboundRepository extends JpaRepository<Inbound, Long> {
    default Inbound getBy(Long inboundNo) {
        return findById(inboundNo).orElseThrow(() -> new IllegalArgumentException("해당 입고가 존재하지 않습니다."));
    }

    @Query("select i from Inbound i join fetch i.inboundItems ii where ii.inboundItemNo = :inboundItemNo")
    Optional<Inbound> findByInboundItemNo(Long inboundItemNo);

    default Inbound getInbound(Long inboundItemNo) {
        return findByInboundItemNo(inboundItemNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 입고 상품이 존재하지 않습니다."));
    }
}
