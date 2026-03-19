package com.nick_bern.Technical.test.services.Impl;

import com.nick_bern.Technical.test.dtos.SaleDTO;
import com.nick_bern.Technical.test.services.ISaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService {
    @Override
    public List<SaleDTO> getAllSales() {
        return List.of();
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

    }
}
