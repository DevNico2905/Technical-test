package com.nick_bern.Technical.test.services;

import com.nick_bern.Technical.test.dtos.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO newProduct);

    ProductDTO updateProduct(Long idProduct, ProductDTO product);

    void deleteProduct(Long idProduct);
}
