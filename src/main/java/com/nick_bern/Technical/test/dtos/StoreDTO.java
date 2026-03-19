package com.nick_bern.Technical.test.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO {
    private Long id;
    private String storeName;
    private  String storeAddress;
}
