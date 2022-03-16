package com.senla.javaee.dto.category;


import com.senla.javaee.dto.product.ProductInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryWithProductDto {
    private CategoryInfoDto category;
    private List<ProductInfoDto> products;

    public CategoryInfoDto getCategory() {
        return category;
    }

    public void setCategory(CategoryInfoDto category) {
        this.category = category;
    }

    public List<ProductInfoDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfoDto> products) {
        this.products = products;
    }

    public CategoryInfoDto getCategoryInfoDto() {
        return category;
    }

    public void setCategoryInfoDto(CategoryInfoDto categoryInfoDto) {
        this.category = categoryInfoDto;
    }
}
