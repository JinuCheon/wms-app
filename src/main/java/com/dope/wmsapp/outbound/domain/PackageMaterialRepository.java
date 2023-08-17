package com.dope.wmsapp.outbound.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageMaterialRepository {
    Long sequence = 1L;
    private Map<Long, PackagingMaterial> packagingMaterials = new HashMap<>();

    public void save(final PackagingMaterial packagingMaterial) {
        packagingMaterials.put(sequence++, packagingMaterial);
    }

    public List<PackagingMaterial> finAll() {
        return List.copyOf(packagingMaterials.values());
    }
}
