package com.nick_bern.Technical.test.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;

    private String productName;

    private String category;

    private Double price;

    private int stock;
}
