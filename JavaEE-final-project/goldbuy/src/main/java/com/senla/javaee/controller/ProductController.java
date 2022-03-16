package com.senla.javaee.controller;


import com.senla.javaee.dto.product.ProductInfoDto;
import com.senla.javaee.security.entity.UserDetailsWithId;
import com.senla.javaee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductInfoDto createProduct(@RequestBody ProductInfoDto productInfoDto) {
        return productService.create(productInfoDto);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductInfoDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductInfoDto updateProduct(@RequestBody ProductInfoDto productInfoDto) {
        return productService.update(productInfoDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductInfoDto deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }

    @GetMapping(value = "/my/{page}/{size}")
    public List<ProductInfoDto> getByUserId(@AuthenticationPrincipal UserDetailsWithId userInf, @PathVariable int page, @PathVariable int size) {
        List<ProductInfoDto> productInfoDtos = productService.getByUserId(userInf.getId());
        return productInfoDtos.subList(page * size, Math.min(productInfoDtos.size(), (page + 1) * size));
    }

    @GetMapping(value = "/by-category/{category}/{page}/{size}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ProductInfoDto> getProductByCategory(@PathVariable String category, @PathVariable int page, @PathVariable int size) {
        List<ProductInfoDto> productInfoDtos = (productService.getByName(category, null));
        return productInfoDtos.subList(page * size, Math.min(productInfoDtos.size(), (page + 1) * size));
    }


    @GetMapping(value = "/by-category/{category}/{page}/{size}/{sort}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ProductInfoDto> getProductByCategoryWithSort(@PathVariable String category, @PathVariable int page,
                                                             @PathVariable int size, @PathVariable String sorting) {
        List<ProductInfoDto> productInfoDtos = (productService.getByName(category, sorting));
        return productInfoDtos.subList(page * size, Math.min(productInfoDtos.size(), (page + 1) * size));
    }

    @GetMapping(value = "/all/{page}/{size}/{sorting}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ProductInfoDto> getAllWithSort(@PathVariable int page, @PathVariable int size, @PathVariable String sorting) {
        List<ProductInfoDto> productInfoDtos = productService.getAll(sorting);

        return productInfoDtos.subList(page * size, Math.min(productInfoDtos.size(), (page + 1) * size));
    }

    @GetMapping(value = "/all/{page}/{size}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ProductInfoDto> getAll(@PathVariable int page, @PathVariable int size) {
        List<ProductInfoDto> productInfoDtos = productService.getAll(null);
        return productInfoDtos.subList(page * size, Math.min(productInfoDtos.size(), (page + 1) * size));
    }


    @PutMapping(value = "my-update/{id}")
    public ProductInfoDto updateYourProduct(@AuthenticationPrincipal UserDetailsWithId userInf,
                                            @RequestBody ProductInfoDto productInfoDto) {
        return productService.updateYour(productInfoDto, userInf.getId());
    }

    @DeleteMapping(value = "my-delete/{id}")
    public ProductInfoDto deleteYourProduct(@AuthenticationPrincipal UserDetailsWithId userInf,
                                            @PathVariable Long id) {
        return productService.deleteYour(userInf.getId(), id);
    }

//    public ProductInfoDto buyProduct(@AuthenticationPrincipal UserDetailsWithId userDetailsWithId) {
//        return productService.buy(id);
//    }
    //todo method buy;
}
