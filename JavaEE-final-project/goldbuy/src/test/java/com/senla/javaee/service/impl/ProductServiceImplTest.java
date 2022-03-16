package com.senla.javaee.service.impl;

import com.senla.javaee.dao.ProductDao;
import com.senla.javaee.dto.product.ProductInfoDto;
import com.senla.javaee.dto.product.ProductWithCategoryDto;
import com.senla.javaee.dto.product.ProductWithUserDto;
import com.senla.javaee.entity.Category;
import com.senla.javaee.entity.Product;
import com.senla.javaee.entity.User;
import com.senla.javaee.service.converter.ProductConverter;
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
class ProductServiceImplTest {
    private final Long id = 1L;
    private final String userName = "oleg";
    private final float price = 100;
    private final String mail = "oleg@gmail.com";
    private final String phoneNumber = "123123";
    private final String productName = "iphone";
    private final String categoryName = "phone";
    private final Category category = Category.builder().id(id).name(categoryName).build();
    private final User user = User.builder().id(id).name(userName).mail(mail).phoneNumber(phoneNumber).build();
    private final Product product = Product.builder().id(id).price(price).name(productName).user(user).category(category).build();
    @Spy
    private ModelMapper mapper;
    @Spy
    @InjectMocks
    private ProductConverter productConverter;
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductDao productDao;

    @Test
    public void testCreate() {
        when(productDao.create(any())).thenReturn(product);
        ProductInfoDto productInfoDto = productService.create(ProductInfoDto
                .builder().id(id).price(price).name(productName).build());
        assertEquals(id, productInfoDto.getId());
        assertEquals(price, productInfoDto.getPrice());
        assertEquals(productName, productInfoDto.getName());
    }

    @Test
    public void testDelete() {
        when(productDao.delete(any())).thenReturn(product);
        ProductInfoDto productInfoDto = productService.delete(id);
        assertEquals(id, productInfoDto.getId());
        assertEquals(price, productInfoDto.getPrice());
        assertEquals(productName, productInfoDto.getName());
    }

    @Test
    public void testUpdate() {
        when(productDao.update(any())).thenReturn(product);
        ProductInfoDto productInfoDto = productService.update(ProductInfoDto
                .builder().id(id).price(price).name(productName).build());
        assertEquals(id, productInfoDto.getId());
        assertEquals(price, productInfoDto.getPrice());
        assertEquals(productName, productInfoDto.getName());
    }

    @Test
    public void testGetById() {
        when(productDao.getById(any())).thenReturn(product);
        ProductInfoDto productInfoDto = productService.getById(id);
        assertEquals(id, productInfoDto.getId());
        assertEquals(price, productInfoDto.getPrice());
        assertEquals(productName, productInfoDto.getName());


    }

    @Test
    public void getProductWithUser() {
        when(productDao.getProductWithUser(any())).thenReturn(product);
        ProductWithUserDto productWithUser = productService.getProductWithUser(id);
        assertEquals(id, productWithUser.getProduct().getId());
        assertEquals(price, productWithUser.getProduct().getPrice());
        assertEquals(productName, productWithUser.getProduct().getName());
        assertEquals(id, productWithUser.getUser().getId());
        assertEquals(phoneNumber, productWithUser.getUser().getPhoneNumber());
        assertEquals(mail, productWithUser.getUser().getMail());
        assertEquals(userName, productWithUser.getUser().getName());
    }

    @Test
    public void getProductWithCategory() {
        when(productDao.getProductWithCategory(any())).thenReturn(product);
        ProductWithCategoryDto productWithCategoryDto = productService.getProductWithCategory(id);
        assertEquals(id, productWithCategoryDto.getProduct().getId());
        assertEquals(price, productWithCategoryDto.getProduct().getPrice());
        assertEquals(productName, productWithCategoryDto.getProduct().getName());
        assertEquals(id, productWithCategoryDto.getCategory().getId());
        assertEquals(categoryName, productWithCategoryDto.getCategory().getName());
    }

}