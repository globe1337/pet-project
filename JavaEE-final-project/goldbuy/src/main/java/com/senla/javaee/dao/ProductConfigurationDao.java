package com.senla.javaee.dao;

import com.senla.javaee.entity.ProductConfiguration;

public interface ProductConfigurationDao extends GenericDao<ProductConfiguration, Long> {
    ProductConfiguration getProductConfigWithProduct(Long id);

    ProductConfiguration getByProductId(Long id);

    ProductConfiguration getProductConfigWithProductChekUserId(Long userId, Long productId);
}
