package com.nick_bern.Technical.test.services;

import com.nick_bern.Technical.test.dtos.SaleDTO;
import java.util.List;

public interface ISaleService {

    List<SaleDTO> getAllSales();

    SaleDTO createSale(SaleDTO newSale);

    SaleDTO updateSale(Long idSale, SaleDTO sale);

    void deleteSale(Long idSale);

}
