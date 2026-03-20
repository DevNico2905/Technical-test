package com.nick_bern.Technical.test.services;

import com.nick_bern.Technical.test.dtos.StoreDTO;

import java.util.List;

public interface IStoreService {

    List<StoreDTO> findAllStores();

    StoreDTO createStore(StoreDTO store);

    StoreDTO updateStore(Long idStore, StoreDTO store);

    void deleteStore(Long idStore);

}
