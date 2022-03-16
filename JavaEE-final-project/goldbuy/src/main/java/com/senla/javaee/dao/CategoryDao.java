package com.senla.javaee.dao;

import com.senla.javaee.entity.Category;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryDao extends GenericDao<Category,Long> {
    Category getCategoryWithProduct(Long id);


    Category getByName(String name);
}
