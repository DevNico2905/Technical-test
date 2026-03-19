package com.nick_bern.Technical.test.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDTO {
    private Long id;

    private LocalDate date;

    private String status;

    private Long storeId;

    private List<SaleDetailDTO> detail;

    private Double total;

}
