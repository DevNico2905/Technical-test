package com.nick_bern.Technical.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailDTO {
    private Long id;
    private String productName;
    private Integer qtyProduct;
    private Double price;
    private Double subtotal;
}
