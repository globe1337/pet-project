package com.senla.javaee.entity;

import com.senla.javaee.configuration.GraphConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "productConfig")
@NamedEntityGraph(name = GraphConfiguration.PRODUCTCONFIG_PRODUCT, attributeNodes = @NamedAttributeNode("product"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private long id;
    @Column(name = "max_price")
    private float maxPrice;
    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "min_Price")
    private float minPrice;
    @Column(name = "price_step")
    private float priceStep;
    @Column(name = "frequency")
    private int frequency;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getPriceStep() {
        return priceStep;
    }

    public void setPriceStep(float priceStep) {
        this.priceStep = priceStep;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


}
