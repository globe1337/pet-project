package com.senla.javaee.service.impl;

import com.senla.javaee.dao.ProductConfigurationDao;
import com.senla.javaee.dto.product_configuration.ProductConfigurationInfoDto;
import com.senla.javaee.dto.product_configuration.ProductConfigurationWithProductDto;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.ProductConfiguration;
import com.senla.javaee.service.converter.ProductConfigurationConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductConfigurationServiceImplTest {
    private final Long id = 1L;
    private final float price = 100;
    private final String productName = "iphone";
    private final float maxPrice = 5000;
    private final float priceStep = 20;
    private final float minPrice = 2500;
    private final int frequency = 12;
    private final Product product = Product.builder().id(id).price(price).name(productName).build();
    private final ProductConfiguration productConfiguration = ProductConfiguration.builder()
            .id(id).product(product).maxPrice(maxPrice).frequency(frequency).priceStep(priceStep).minPrice(minPrice).build();
    @Spy
    private ModelMapper mapper;
    @Spy
    @InjectMocks
    private ProductConfigurationConverter productConfigurationConverter
    @InjectMocks
    private ProductConfigurationServiceImpl productConfigurationService;
    @Mock
    private ProductConfigurationDao productConfigurationDao;

    @Test
    public void testCreate() {
        when(productConfigurationDao.create(any())).thenReturn(productConfiguration);
        ProductConfigurationInfoDto productConfigurationInfoDto = productConfigurationService.create(ProductConfigurationInfoDto.builder()
                .id(id).maxPrice(maxPrice).priceStep(priceStep).minPrice(minPrice).build());
        assertEquals(id, productConfigurationInfoDto.getId());
        assertEquals(maxPrice, productConfigurationInfoDto.getMaxPrice());
        assertEquals(frequency, productConfigurationInfoDto.getFrequency());
        assertEquals(minPrice, productConfigurationInfoDto.getMinPrice());
        assertEquals(priceStep, productConfigurationInfoDto.getPriceStep());


    }

    @Test
    public void testDelete() {
        when(productConfigurationDao.delete(any())).thenReturn(productConfiguration);
        ProductConfigurationInfoDto productConfigurationInfoDto = productConfigurationService.delete(id);
        assertEquals(id, productConfigurationInfoDto.getId());
        assertEquals(maxPrice, productConfigurationInfoDto.getMaxPrice());
        assertEquals(frequency, productConfigurationInfoDto.getFrequency());
        assertEquals(minPrice, productConfigurationInfoDto.getMinPrice());
        assertEquals(priceStep, productConfigurationInfoDto.getPriceStep());
    }

    @Test
    public void testUpdate() {
        when(productConfigurationDao.update(any())).thenReturn(productConfiguration);
        ProductConfigurationInfoDto productConfigurationInfoDto = productConfigurationService.update(ProductConfigurationInfoDto.builder()
                .id(id).maxPrice(maxPrice).priceStep(priceStep).minPrice(minPrice).build());
        assertEquals(id, productConfigurationInfoDto.getId());
        assertEquals(maxPrice, productConfigurationInfoDto.getMaxPrice());
        assertEquals(frequency, productConfigurationInfoDto.getFrequency());
        assertEquals(minPrice, productConfigurationInfoDto.getMinPrice());
        assertEquals(priceStep, productConfigurationInfoDto.getPriceStep());
    }

    @Test
    public void testGetById() {
        when(productConfigurationDao.getById(any())).thenReturn(productConfiguration);
        ProductConfigurationInfoDto productConfigurationInfoDto = productConfigurationService.getById(id);
        assertEquals(id, productConfigurationInfoDto.getId());
        assertEquals(maxPrice, productConfigurationInfoDto.getMaxPrice());
        assertEquals(frequency, productConfigurationInfoDto.getFrequency());
        assertEquals(minPrice, productConfigurationInfoDto.getMinPrice());
        assertEquals(priceStep, productConfigurationInfoDto.getPriceStep());
    }

    @Test
    public void getProductWithUser() {
        when(productConfigurationDao.getProductConfigWithProduct(any())).thenReturn(productConfiguration);
        ProductConfigurationWithProductDto productConfigurationWithProductDto = productConfigurationService.getProductConfigWithProduct(id);
        assertEquals(id, productConfigurationWithProductDto.getProductConfiguration().getId());
        assertEquals(maxPrice, productConfigurationWithProductDto.getProductConfiguration().getMaxPrice());
        assertEquals(frequency, productConfigurationWithProductDto.getProductConfiguration().getFrequency());
        assertEquals(minPrice, productConfigurationWithProductDto.getProductConfiguration().getMinPrice());
        assertEquals(priceStep, productConfigurationWithProductDto.getProductConfiguration().getPriceStep());
        assertEquals(id, productConfigurationWithProductDto.getProduct().getId());
        assertEquals(productName, productConfigurationWithProductDto.getProduct().getName());
        assertEquals(price, productConfigurationWithProductDto.getProduct().getPrice());

    }

}