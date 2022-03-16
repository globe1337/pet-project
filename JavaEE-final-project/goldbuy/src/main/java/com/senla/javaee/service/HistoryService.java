package com.senla.javaee.service;

import com.senla.javaee.dto.history.HistoryInfoDto;
import com.senla.javaee.dto.history.HistoryWithCustomerDto;
import com.senla.javaee.dto.history.HistoryWithProductDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HistoryService {
    HistoryInfoDto delete(Long id);

    HistoryInfoDto create(HistoryInfoDto historyDto);

    HistoryInfoDto update(HistoryInfoDto historyDto);

    HistoryInfoDto getById(Long id);

    HistoryWithProductDto getHistoryWithProduct(Long userId, Long productId);

    HistoryWithProductDto getHistoryWithProduct(Long productId);


    HistoryWithCustomerDto getHistoryWithCustomer(Long id);

    HistoryWithCustomerDto getHistoryWithCustomer(Long userId, Long productId);


    List<HistoryInfoDto> getByUserId(long id);

    HistoryInfoDto getByProductId(Long productId, Long userId);
}
