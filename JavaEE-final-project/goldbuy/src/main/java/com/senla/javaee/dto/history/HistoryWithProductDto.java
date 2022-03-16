package com.senla.javaee.dto.history;

import com.senla.javaee.dto.product.ProductInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryWithProductDto {
    private HistoryInfoDto history;
    private ProductInfoDto product;

    public ProductInfoDto getProduct() {
        return product;
    }

    public void setProduct(ProductInfoDto product) {
        this.product = product;
    }

    public HistoryInfoDto getHistory() {
        return history;
    }

    public void setHistory(HistoryInfoDto history) {
        this.history = history;
    }
}
