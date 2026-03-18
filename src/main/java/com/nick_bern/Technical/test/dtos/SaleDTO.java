package com.nick_bern.Technical.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Long id;

    private LocalDate date;

    private String status;

    private Long storeId;

    private List<SaleDetailDTO> detail;

    private Double total;

}
