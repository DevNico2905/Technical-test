package com.nick_bern.Technical.test.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailDTO {
    private Long id;
    private String productName;
    private Integer qtyProduct;
    private Double price;
    private Double subtotal;
}
