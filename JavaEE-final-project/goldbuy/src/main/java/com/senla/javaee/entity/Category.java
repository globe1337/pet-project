package com.senla.javaee.entity;

import com.senla.javaee.configuration.GraphConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = GraphConfiguration.CATEGORY_PRODUCTS, attributeNodes = @NamedAttributeNode("products"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id",insertable = false, updatable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
