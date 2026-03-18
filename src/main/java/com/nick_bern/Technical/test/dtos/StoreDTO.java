package com.nick_bern.Technical.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private Long id;
    private String storeName;
    private  String storeAddress;
}
