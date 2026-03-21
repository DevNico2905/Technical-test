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
    public SaleDTO createSale(SaleDTO sale) {

        if (sale == null) throw new RuntimeException("Sale is null");
        if (sale.getStoreId() == null) throw new RuntimeException("A store must be include!");
        if (sale.getDetail() == null || sale.getDetail().isEmpty())
            throw new RuntimeException("At least a product have to be included");

        Store store = storeRepo.findById(sale.getId()).orElseThrow(() -> new NotFoundException("Store not found!"));

        List<SaleDetail> details = new ArrayList<>();

        for (SaleDetailDTO sdDTO : sale.getDetail()){
            Product product = productRepo.findProductByProductName(sdDTO.getProductName())
                    .orElseThrow(() -> new NotFoundException("Product with id:" + sdDTO.getId() + " not found!"));

            SaleDetail detail = SaleDetail.builder()
                    .id(sdDTO.getId())
                    .sale(saleRepo.findById(sdDTO.getId())
                            .orElseThrow(() -> new NotFoundException("Sale not found")))
                    .product(product)
                    .quantity(sdDTO.getQtyProduct())
                    .unitPrice(sdDTO.getPrice())
                    .build();

            details.add(detail);
        }

        Sale newSale = Sale.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .status(sale.getStatus())
                .total(sale.getTotal())
                .store(store)
                .details(details)
                .build();

        return Mappers.toDTO(saleRepo.save(newSale));
    }

    @Override
    public SaleDTO updateSale(Long idSale, SaleDTO sale) {
        return null;
    }

    @Override
    public void deleteSale(Long idSale) {
        if (!saleRepo.existsById(idSale)){
            throw new NotFoundException("Sale with id: " + idSale + " not found!");
        } else {
            saleRepo.deleteById(idSale);
        }
    }
}
