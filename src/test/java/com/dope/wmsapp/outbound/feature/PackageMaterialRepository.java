package com.dope.wmsapp.outbound.feature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PackageMaterialRepository {
    Long sequence = 1L;
    private Map<Long, PackagingMaterial> packagingMaterials = new HashMap<>();

    public void save(final PackagingMaterial packagingMaterial) {
        packagingMaterials.put(sequence++, packagingMaterial);
    }

    public List<PackagingMaterial> finAll() {
        return List.copyOf(packagingMaterials.values());
    }
}
