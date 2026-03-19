package com.nick_bern.Technical.test.mappers;

import com.nick_bern.Technical.test.dtos.ProductDTO;
import com.nick_bern.Technical.test.dtos.SaleDTO;
import com.nick_bern.Technical.test.dtos.SaleDetailDTO;
import com.nick_bern.Technical.test.dtos.StoreDTO;
import com.nick_bern.Technical.test.models.Product;
import com.nick_bern.Technical.test.models.Sale;
import com.nick_bern.Technical.test.models.Store;

public class Mappers {

    // Map Product to DTO
    public static ProductDTO toDTO(Product p){
        if (p == null) return null;

        return ProductDTO.builder()
                .id(p.getId())
                .productName(p.getProductName())
                .category(p.getCategory())
                .price(p.getPrice())
                .stock(p.getStock())
                .build();
    }

    // Map Sale to DTO
    public static SaleDTO toDTO(Sale sa){
        if (sa == null) return null;

        var detail = sa.getDetails().stream().map(
                sd -> SaleDetailDTO.builder()
                        .id(sd.getId())
                        .productName(sd.getProduct().getProductName())
                        .qtyProduct(sd.getQuantity())
                        .price(sd.getUnitPrice())
                        .subtotal(sd.getUnitPrice() * sd.getQuantity())
                        .build()
        ).toList();

        var total = detail.stream()
                .map(SaleDetailDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return SaleDTO.builder()
                .id(sa.getId())
                .date(sa.getDate())
                .status(sa.getStatus())
                .storeId(sa.getStore().getId())
                .detail(detail)
                .total(total)
                .build();
    }


    // Map Store to DTO
    public static StoreDTO toDTO(Store so){
        if (so == null) return null;

        return  StoreDTO.builder()
                .id(so.getId())
                .storeName(so.getStoreName())
                .storeAddress(so.getStoreAddress())
                .build();
    }
}
