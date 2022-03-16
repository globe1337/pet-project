package com.senla.javaee.service.impl;


import com.senla.javaee.dao.ProductDao;
import com.senla.javaee.dto.product.ProductInfoDto;
import com.senla.javaee.dto.product.ProductWithCategoryDto;
import com.senla.javaee.dto.product.ProductWithUserDto;
import com.senla.javaee.entity.Product;
import com.senla.javaee.service.ProductService;
import com.senla.javaee.service.converter.ProductConverter;
import com.senla.javaee.service.exception.ProductNotFoundException;
import com.senla.javaee.service.exception.ProductNotUpdateExcetpion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Component
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final ProductConverter productConverter;

    @Override
    public ProductInfoDto delete(Long id) {
        return productConverter.convert(productDao.delete(id));
    }

    @Override
    public ProductInfoDto create(ProductInfoDto productDto) {
        Product product = productConverter.convert(productDto);
        return productConverter.convert(productDao.create(product));
    }

    @Override
    public ProductInfoDto update(ProductInfoDto productDto) {
        Product product = productConverter.convert(productDto);
        return productConverter.convert(productDao.update(product));
    }

    @Override
    public ProductInfoDto getById(Long id) {
        final Product product = ofNullable(productDao.getById(id))
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productConverter.convert(product);

    }

    @Override
    public ProductInfoDto getMostExpensiveProduct() {
        return productConverter.convert(productDao.getMostExpensiveProduct());
    }

    @Override
    public ProductWithUserDto getProductWithUser(Long id) {
        return productConverter.convertWithUser(productDao.getProductWithUser(id));
    }

    @Override
    public ProductWithCategoryDto getProductWithCategory(Long id) {
        return productConverter.convertWithCategory(productDao.getProductWithCategory(id));

    }

    @Override
    public List<ProductInfoDto> getActiveProducts() {
        return productConverter.convert(productDao.getActiveProducts());
    }

    @Override
    public List<ProductInfoDto> getByUserId(Long id) {

        return productConverter.convert(productDao.getByUserId(id));
    }

    @Override
    public ProductInfoDto updateYour(ProductInfoDto productInfoDto, Long userId) {
        if (productDao.getProductWithUser(productInfoDto.getId()).getUser().getId() == userId) {
            Product product = productDao.update(productConverter.convert(productInfoDto));
            return productConverter.convert(product);
        }
        throw new ProductNotUpdateExcetpion(productInfoDto.getId());
    }

    @Override
    public ProductInfoDto deleteYour(Long userId, Long productId) {
        if (productDao.getProductWithUser(userId).getUser().getId() == userId) {
            return productConverter.convert(productDao.delete(productId));
        }
        throw new ProductNotFoundException(productId);
    }

    @Override
    public List<ProductInfoDto> getByName(String category,String sorting) {
        return productConverter.convert(productDao.getProductByCategory(category,sorting));
    }

    @Override
    public List<ProductInfoDto> getAll(String sorting) {
        return productConverter.convert(productDao.getAll(sorting));
    }
}

