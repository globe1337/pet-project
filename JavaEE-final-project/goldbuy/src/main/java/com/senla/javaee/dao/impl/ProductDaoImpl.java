package com.senla.javaee.dao.impl;

import com.senla.javaee.configuration.GraphConfiguration;
import com.senla.javaee.dao.ProductDao;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.Product_;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl extends AbstractDao<Product, Long> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public Product getMostExpensiveProduct() {
        return entityManager.
                createQuery("SELECT p from Product p where price=(select max(pr.price) from Product pr)",
                        Product.class).getSingleResult();
    }

    @Override
    public List<Product> getActiveProducts() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        return entityManager.createQuery(query.select(root).where(criteriaBuilder.equal(root.get(Product_.status), "active"))).getResultList();
    }

    @Override
    public Product getProductWithUser(Long id) {
        EntityGraph userGraph = entityManager.getEntityGraph(GraphConfiguration.PRODUCT_USER);
        Map hints = new HashMap();
        hints.put(GRAPH_PERSISTENCE, userGraph);
        return entityManager.find(Product.class, id, hints);
    }

    @Override
    public Product getProductWithCategory(Long id) {
        EntityGraph userGraph = entityManager.getEntityGraph(GraphConfiguration.PRODUCT_CATEGORY);
        Map hints = new HashMap();
        hints.put(GRAPH_PERSISTENCE, userGraph);
        return entityManager.find(Product.class, id, hints);
    }

    @Override
    public List<Product> getByUserId(long id) {
        Product products=getProductWithUser(1L);
        List<Product> product=entityManager.createQuery
                ("select product from Product product", Product.class).getResultList();
        return entityManager.createQuery
                        ("select product from Product product join fetch product.user u where u.id= :id", Product.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<Product> getProductByCategory(String category,String sorting) {
        if(sorting==null){
            sorting="id";
        }
        return entityManager.
                createQuery("SELECT p from Product p join fetch p.category c where c.name= :category ORDER BY p."+sorting,
                        Product.class).setParameter("category",category).getResultList();
    }

    @Override
    public List<Product> getAll(String sorting) {
        if (sorting==null){
            sorting="id";
        }
        return entityManager.
                createQuery("SELECT p from Product p ORDER BY p."+sorting,Product.class).getResultList();
    }
}
