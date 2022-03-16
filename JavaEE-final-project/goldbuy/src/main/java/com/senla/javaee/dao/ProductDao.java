package com.senla.javaee.dao;

import com.senla.javaee.entity.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product,Long>{
    Product getMostExpensiveProduct();

    List<Product> getActiveProducts();

    Product getProductWithUser(Long id);

    Product getProductWithCategory(Long id);

    List<Product> getByUserId(long id);

    List<Product> getProductByCategory(String category,String sorting);

    List<Product> getAll(String sorting);

}
