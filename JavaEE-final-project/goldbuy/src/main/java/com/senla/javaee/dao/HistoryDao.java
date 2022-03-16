package com.senla.javaee.dao;

import com.senla.javaee.entity.History;

import java.util.List;


public interface HistoryDao extends GenericDao<History, Long> {
    History getHistoryWithProduct(Long productId);

    History getHistoryWithCustomer(Long customerId,Long historyId);

    History getHistoryWithCustomer(Long id);

    List<History> getByUserId(Long id);

    History getByProductId(Long productId, Long userId);
}
