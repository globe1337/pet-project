package com.senla.javaee.dto.product;

import com.senla.javaee.dto.category.CategoryInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductWithCategoryDto {
    private ProductInfoDto product;
    private CategoryInfoDto category;

    public ProductInfoDto getProduct() {
        return product;
    }

    public void setProduct(ProductInfoDto product) {
        this.product = product;
    }

    public CategoryInfoDto getCategory() {
        return category;
    }

    public void setCategory(CategoryInfoDto category) {
        this.category = category;
    }
}