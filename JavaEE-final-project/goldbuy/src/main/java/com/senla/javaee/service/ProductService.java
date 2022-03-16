package com.senla.javaee.service;

import com.senla.javaee.dto.product.ProductInfoDto;
import com.senla.javaee.dto.product.ProductWithCategoryDto;
import com.senla.javaee.dto.product.ProductWithUserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductService {
    ProductInfoDto delete(Long id);

    ProductInfoDto create(ProductInfoDto productDto);

    ProductInfoDto update(ProductInfoDto productDto);

    ProductInfoDto getById(Long id);

    ProductInfoDto getMostExpensiveProduct();

    ProductWithUserDto getProductWithUser(Long id);

    ProductWithCategoryDto getProductWithCategory(Long id);

    List<ProductInfoDto> getActiveProducts();

    List<ProductInfoDto> getByUserId(Long id);

    ProductInfoDto updateYour(ProductInfoDto productInfoDto,Long userId);

    ProductInfoDto deleteYour(Long userId, Long productId);

    List<ProductInfoDto> getByName(String category,String sorting);

    List<ProductInfoDto> getAll(String sorting);
}
