package com.senla.javaee.service.impl;

import com.senla.javaee.dao.HistoryDao;
import com.senla.javaee.dto.history.HistoryInfoDto;
import com.senla.javaee.dto.history.HistoryWithCustomerDto;
import com.senla.javaee.dto.history.HistoryWithProductDto;
import com.senla.javaee.entity.History;
import com.senla.javaee.service.HistoryService;
import com.senla.javaee.service.converter.HistoryConverter;
import com.senla.javaee.service.exception.HistoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;


@Component
@Transactional
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryDao historyDao;
    private final HistoryConverter historyConverter;

    @Override
    public HistoryInfoDto delete(Long id) {
        return historyConverter.convert(historyDao.delete(id));
    }


    @Override
    public HistoryInfoDto create(HistoryInfoDto historyDto) {
        History history = historyConverter.convert(historyDto);
        return historyConverter.convert(historyDao.create(history));
    }

    @Override
    public HistoryInfoDto update(HistoryInfoDto historyDto) {
        History history = historyConverter.convert(historyDto);
        return historyConverter.convert(historyDao.update(history));
    }

    @Override
    public HistoryInfoDto getById(Long id) {
        final History history = ofNullable(historyDao.getById(id))
                .orElseThrow(() -> new HistoryNotFoundException(id));
        return historyConverter.convert(history);
    }

    @Override
    public HistoryWithProductDto getHistoryWithProduct(Long userId, Long productId) {
        History history = ofNullable(historyDao.getByProductId(userId, productId))
                .orElseThrow(() -> new HistoryNotFoundException(productId));
        if (history.getCustomer().getId() != userId) {
            throw new HistoryNotFoundException(productId);
        }
        return historyConverter.convertWithProduct(history);
    }
    @Override
    public HistoryWithProductDto getHistoryWithProduct(Long productId) {
        History history = ofNullable(historyDao.getHistoryWithProduct(productId))
                .orElseThrow(() -> new HistoryNotFoundException(productId));
        return historyConverter.convertWithProduct(history);
    }

    @Override
    public HistoryWithCustomerDto getHistoryWithCustomer(Long userId, Long historyId) {
        History history = ofNullable(historyDao.getHistoryWithCustomer(userId, historyId))
                .orElseThrow(() -> new HistoryNotFoundException(historyId));
        if (history.getCustomer().getId() != userId) {
            throw new HistoryNotFoundException(historyId);
        }
        return historyConverter.convertWithCustomer(history);
    }

    @Override
    public HistoryWithCustomerDto getHistoryWithCustomer(Long historyId) {
        History history = ofNullable(historyDao.getHistoryWithCustomer(historyId))
                .orElseThrow(() -> new HistoryNotFoundException(historyId));
        return historyConverter.convertWithCustomer(history);
    }

    @Override
    public List<HistoryInfoDto> getByUserId(long id) {
        List<History> historyList = ofNullable(historyDao.getByUserId(id))
                .orElseThrow(() -> new HistoryNotFoundException(id));
        return historyConverter.convert(historyList);
    }

    @Override
    public HistoryInfoDto getByProductId(Long productId, Long userId) {
        History history = ofNullable(historyDao.getByProductId(productId, userId))
                .orElseThrow(HistoryNotFoundException::new);
        return historyConverter.convert(history);
    }
}
