package com.senla.javaee.service;

import com.senla.javaee.dto.product_configuration.ProductConfigurationInfoDto;
import com.senla.javaee.dto.product_configuration.ProductConfigurationWithProductDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductConfigurationService {
    ProductConfigurationInfoDto delete(Long id);

    ProductConfigurationInfoDto create(ProductConfigurationInfoDto productConfigurationDto);

    ProductConfigurationInfoDto update(ProductConfigurationInfoDto productConfigurationDto);

    ProductConfigurationInfoDto getById(Long id);

    ProductConfigurationWithProductDto getProductConfigWithProduct(Long id);

    ProductConfigurationInfoDto getByProductId(Long id);

    ProductConfigurationWithProductDto getProductConfigWithProductChekUserId(Long userId, Long productId);

    ProductConfigurationInfoDto update(Long userId,ProductConfigurationInfoDto productConfigurationDto);

}
