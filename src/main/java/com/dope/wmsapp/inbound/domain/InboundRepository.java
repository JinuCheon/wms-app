package com.dope.wmsapp.inbound.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InboundRepository {
    private final Map<Long, Inbound> inbounds = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Inbound inbound) {
        inbound.assignedId(sequence++);
        inbounds.put(inbound.getId(), inbound);
    }

    public Map<Long, Inbound> findAll() {
        return inbounds;
    }
}
