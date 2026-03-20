package com.nick_bern.Technical.test.services.Impl;

import com.nick_bern.Technical.test.dtos.ProductDTO;
import com.nick_bern.Technical.test.mappers.Mappers;
import com.nick_bern.Technical.test.models.Product;
import com.nick_bern.Technical.test.repositories.ProductRepository;
import com.nick_bern.Technical.test.services.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.List;

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
    @SneakyThrows
    public ProductDTO updateProduct(Long idProduct, ProductDTO productDTO) {
        Product productEntity = productRepo.findById(idProduct)
                .orElseThrow(() -> new Exception("Product with id: " + idProduct + " not found!"));

        productEntity.setProductName(productDTO.getProductName());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setStock(productDTO.getStock());
        productEntity.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepo.save(productEntity);

        return Mappers.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long idProduct) {

    }
}
