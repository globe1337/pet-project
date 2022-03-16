package com.senla.javaee.service.impl;

import com.senla.javaee.dao.HistoryDao;
import com.senla.javaee.dto.history.HistoryInfoDto;
import com.senla.javaee.dto.history.HistoryWithCustomerDto;
import com.senla.javaee.dto.history.HistoryWithProductDto;
import com.senla.javaee.entity.History;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.User;
import com.senla.javaee.service.converter.HistoryConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {
    private final Long id = 1L;
    private final String customerName = "oleg";
    private final float price = 100;
    private final String mail = "oleg@gmail.com";
    private final String phoneNumber = "123123";
    private final String productName = "iphone";
    private final User customer = User.builder().id(id).name(customerName).mail(mail).phoneNumber(phoneNumber).build();
    private final Product product = Product.builder().id(id).price(price).name(productName).build();
    private final History history = History.builder().customer(customer).product(product).id(id).sellingPrice(price).build();
    @Spy
    private ModelMapper mapper;
    @Spy
    @InjectMocks
    private HistoryConverter historyConverter;
    @InjectMocks
    private HistoryServiceImpl historyService;
    @Mock
    private HistoryDao historyDao;

    @Test
    public void testCreate() {
        when(historyDao.create(any())).thenReturn(history);
        HistoryInfoDto historyInfoDto = historyService.create(HistoryInfoDto
                .builder().sellingPrice(price).build());
        assertEquals(id, historyInfoDto.getId());
        assertEquals(price, historyInfoDto.getSellingPrice());

    }

    @Test
    public void testDelete() {
        when(historyDao.delete(any())).thenReturn(history);
        HistoryInfoDto historyInfoDto = historyService.delete(id);
        assertEquals(id, historyInfoDto.getId());
        assertEquals(price, historyInfoDto.getSellingPrice());
    }

    @Test
    public void testUpdate() {
        when(historyDao.update(any())).thenReturn(history);
        HistoryInfoDto historyInfoDto = historyService.update(HistoryInfoDto
                .builder().sellingPrice(price).build());
        assertEquals(id, historyInfoDto.getId());
        assertEquals(price, historyInfoDto.getSellingPrice());
    }

    @Test
    public void testGetById() {
        when(historyDao.getById(any())).thenReturn(history);
        HistoryInfoDto historyInfoDto = historyService.getById(id);
        assertEquals(id, historyInfoDto.getId());
        assertEquals(price, historyInfoDto.getSellingPrice());


    }

    @Test
    public void getHistoryWithProduct() {
        when(historyDao.getHistoryWithProduct(any())).thenReturn(history);
        HistoryWithProductDto historyWithProductDto = historyService.getHistoryWithProduct(1L,id);
        assertEquals(id, historyWithProductDto.getHistory().getId());
        assertEquals(price, historyWithProductDto.getHistory().getSellingPrice());
        assertEquals(id, historyWithProductDto.getProduct().getId());
        assertEquals(productName, historyWithProductDto.getProduct().getName());
        assertEquals(price, historyWithProductDto.getProduct().getPrice());
    }

    @Test
    public void getHistoryWithCustomer() {
        when(historyDao.getHistoryWithCustomer(any())).thenReturn(history);
        HistoryWithCustomerDto historyWithCustomerDto = historyService.getHistoryWithCustomer(id);
        assertEquals(id, historyWithCustomerDto.getHistory().getId());
        assertEquals(price, historyWithCustomerDto.getHistory().getSellingPrice());
        assertEquals(id, historyWithCustomerDto.getCustomer().getId());
        assertEquals(customerName, historyWithCustomerDto.getCustomer().getName());
        assertEquals(phoneNumber, historyWithCustomerDto.getCustomer().getPhoneNumber());
        assertEquals(mail, historyWithCustomerDto.getCustomer().getMail());
    }
}