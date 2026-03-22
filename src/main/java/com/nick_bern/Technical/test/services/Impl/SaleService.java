package com.nick_bern.Technical.test.services.Impl;

import com.nick_bern.Technical.test.dtos.SaleDTO;
import com.nick_bern.Technical.test.dtos.SaleDetailDTO;
import com.nick_bern.Technical.test.exceptions.NotFoundException;
import com.nick_bern.Technical.test.mappers.Mappers;
import com.nick_bern.Technical.test.models.Product;
import com.nick_bern.Technical.test.models.Sale;
import com.nick_bern.Technical.test.models.SaleDetail;
import com.nick_bern.Technical.test.models.Store;
import com.nick_bern.Technical.test.repositories.ProductRepository;
import com.nick_bern.Technical.test.repositories.SaleRepository;
import com.nick_bern.Technical.test.repositories.StoreRepository;
import com.nick_bern.Technical.test.services.ISaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaleService implements ISaleService {

    private final SaleRepository saleRepo;
    private final ProductRepository productRepo;
    private final StoreRepository storeRepo;

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepo.findAll()
                .stream().map(Mappers::toDTO).toList();
    }

    @Override
    @Transactional
    public SaleDTO createSale(SaleDTO sale) {

        validateSale(sale);

        Store store = storeRepo.findById(sale.getStoreId()).orElseThrow(() -> new NotFoundException("Store not found!"));

        Sale newSale = Sale.builder()
                .date(sale.getDate())
                .status(sale.getStatus())
                .store(store)
                .build();

        double total = 0;
        List<SaleDetail> details = new ArrayList<>();

        for (SaleDetailDTO sdDTO : sale.getDetail()){
            Product product = productRepo.findProductByProductName(sdDTO.getProductName())
                    .orElseThrow(() -> new NotFoundException("Product " + sdDTO.getProductName() + " not found!"));

            SaleDetail detail = SaleDetail.builder()
                    .sale(newSale)
                    .product(product)
                    .quantity(sdDTO.getQtyProduct())
                    .unitPrice(product.getPrice())
                    .build();

            total += detail.getUnitPrice() * detail.getQuantity();

            details.add(detail);
        }

        newSale.setTotal(total);
        newSale.setDetails(details);

        return Mappers.toDTO(saleRepo.save(newSale));
    }

    @Override
    @Transactional
    public SaleDTO updateSale(Long idSale, SaleDTO sale) {
        Sale saleEntity = saleRepo.findById(idSale)
                .orElseThrow(() -> new NotFoundException("Sale not found!"));

        validateSale(sale);

        Store store = storeRepo.findById(sale.getStoreId()).orElseThrow(()  -> new NotFoundException("Store not found!"));

        saleEntity.setDate(sale.getDate());
        saleEntity.setStatus(sale.getStatus());
        saleEntity.setStore(store);

        saleEntity.getDetails().clear();

        double total = 0;
        for (SaleDetailDTO SdDtoEntity : sale.getDetail()){
            Product productEntity = productRepo.findProductByProductName(SdDtoEntity.getProductName())
                    .orElseThrow(() -> new NotFoundException("Product " + SdDtoEntity.getProductName() + " not found!"));

            SaleDetail detail = SaleDetail.builder()
                    .sale(saleEntity)
                    .product(productEntity)
                    .quantity(SdDtoEntity.getQtyProduct())
                    .unitPrice(productEntity.getPrice())
                    .build();

            total += detail.getUnitPrice() * detail.getQuantity();
            saleEntity.getDetails().add(detail);
        }

        saleEntity.setTotal(total);

        return Mappers.toDTO(saleRepo.save(saleEntity));
    }

    @Override
    public void deleteSale(Long idSale) {
        if (!saleRepo.existsById(idSale)){
            throw new NotFoundException("Sale with id: " + idSale + " not found!");
        } else {
            saleRepo.deleteById(idSale);
        }
    }


    private void validateSale(SaleDTO saleDTO) {
        if (saleDTO == null) {
            throw new RuntimeException("Sale data is required (null)");
        }
        if (saleDTO.getStoreId() == null) {
            throw new RuntimeException("A store ID must be included!");
        }
        if (saleDTO.getDetail() == null || saleDTO.getDetail().isEmpty()) {
            throw new RuntimeException("At least one product must be included in the sale");
        }
    }
}
