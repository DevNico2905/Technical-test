package com.nick_bern.Technical.test.services.Impl;

import com.nick_bern.Technical.test.dtos.SaleDTO;
import com.nick_bern.Technical.test.exceptions.NotFoundException;
import com.nick_bern.Technical.test.mappers.Mappers;
import com.nick_bern.Technical.test.repositories.SaleRepository;
import com.nick_bern.Technical.test.services.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SaleService implements ISaleService {

    private final SaleRepository saleRepo;

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepo.findAll()
                .stream().map(Mappers::toDTO).toList();
    }

    @Override
    public SaleDTO createSale(SaleDTO newSale) {
        return null;
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
