package com.senla.javaee.service.converter;

import com.senla.javaee.dto.history.HistoryInfoDto;
import com.senla.javaee.dto.history.HistoryWithCustomerDto;
import com.senla.javaee.dto.history.HistoryWithProductDto;
import com.senla.javaee.entity.History;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoryConverter {
    @Autowired
    private ModelMapper mapper;

    public History convert(HistoryInfoDto historyInfoDto) {
        return mapper.map(historyInfoDto, History.class);
    }

    public List<HistoryInfoDto> convert(List<History> historyList) {
        return historyList.stream().
                map(history -> mapper.map(history, HistoryInfoDto.class))
                .collect(Collectors.toList());
    }

    public HistoryInfoDto convert(History history) {
        return mapper.map(history, HistoryInfoDto.class);
    }

    public History convert(HistoryWithProductDto historyInfoDto) {
        return mapper.map(historyInfoDto, History.class);
    }

    public History convert(HistoryWithCustomerDto historyInfoDto) {
        return mapper.map(historyInfoDto, History.class);
    }

    public HistoryWithCustomerDto convertWithCustomer(History history) {
        return mapper.map(history, HistoryWithCustomerDto.class);
    }

    public HistoryWithProductDto convertWithProduct(History history) {
        return mapper.map(history, HistoryWithProductDto.class);
    }
}