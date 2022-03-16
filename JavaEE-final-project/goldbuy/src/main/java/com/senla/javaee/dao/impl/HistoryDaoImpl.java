package com.senla.javaee.dao.impl;

import com.senla.javaee.configuration.GraphConfiguration;
import com.senla.javaee.dao.HistoryDao;
import com.senla.javaee.entity.History;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HistoryDaoImpl extends AbstractDao<History, Long> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class);
    }

    @Override
    public History getHistoryWithProduct(Long id) {
        EntityGraph userGraph = entityManager.getEntityGraph(GraphConfiguration.HISTORY_PRODUCT);
        Map hints = new HashMap();
        hints.put(GRAPH_PERSISTENCE, userGraph);
        return entityManager.find(History.class, id, hints);
    }

    @Override
    public History getHistoryWithCustomer(Long customerId, Long historyId) {
        return entityManager.createQuery
                        ("select history from History history inner join fetch history.customer customer where customer.id= :customerId and history.id= :historyId", History.class)
                .setParameter("customerId", customerId).setParameter("historyId", historyId).getSingleResult();
    }

    @Override
    public History getHistoryWithCustomer(Long id) {
        EntityGraph userGraph = entityManager.getEntityGraph(GraphConfiguration.HISTORY_PRODUCT);
        Map hints = new HashMap();
        hints.put(GRAPH_PERSISTENCE, userGraph);
        return entityManager.find(History.class, id, hints);

    }

    @Override
    public List<History> getByUserId(Long id) {
        return entityManager.createQuery
                        ("select history from History history inner join fetch history.owner user where user.id= :id", History.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public History getByProductId(Long productId, Long userId) {
        return entityManager.createQuery("select history from History history inner join fetch history.owner user inner join fetch history.product product where user.id= :userId and product.id= :productId",
                History.class).setParameter("userId", userId).setParameter("productId", productId).getSingleResult();
    }
}
