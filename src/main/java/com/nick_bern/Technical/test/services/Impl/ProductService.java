package com.nick_bern.Technical.test.services.Impl;

import com.nick_bern.Technical.test.dtos.ProductDTO;
import com.nick_bern.Technical.test.mappers.Mappers;
import com.nick_bern.Technical.test.models.Product;
import com.nick_bern.Technical.test.repositories.ProductRepository;
import com.nick_bern.Technical.test.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepo;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll()
                .stream().map(Mappers::toDTO).toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO newProduct) {
        var product = Product.builder()
                .id(newProduct.getId())
                .productName(newProduct.getProductName())
                .category(newProduct.getCategory())
                .price(newProduct.getPrice())
                .stock(newProduct.getStock())
                .build();
        return Mappers.toDTO(productRepo.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long idProduct, ProductDTO product) {
        Optional<Product> productExists = productRepo.findById(idProduct);
        return null;
    }

    @Override
    public void deleteProduct(Long idProduct) {

    }
}
