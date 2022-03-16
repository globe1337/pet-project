package com.senla.javaee.dto.product_configuration;

import com.senla.javaee.dto.product.ProductInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductConfigurationWithProductDto {

    private ProductInfoDto product;
    private ProductConfigurationInfoDto productConfiguration;

    public ProductInfoDto getProduct() {
        return product;
    }

    public void setProduct(ProductInfoDto product) {
        this.product = product;
    }

    public ProductConfigurationInfoDto getProductConfiguration() {
        return productConfiguration;
    }

    public void setProductConfiguration(ProductConfigurationInfoDto productConfiguration) {
        this.productConfiguration = productConfiguration;
    }
}
