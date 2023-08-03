package com.dope.wmsapp.product.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public void save(Product product) {
        product.assignId(nextId++);
        products.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
