package com.nick_bern.Technical.test.services.Impl;

import com.nick_bern.Technical.test.dtos.StoreDTO;
import com.nick_bern.Technical.test.mappers.Mappers;
import com.nick_bern.Technical.test.repositories.StoreRepository;
import com.nick_bern.Technical.test.services.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService  implements IStoreService {

    private final StoreRepository storeRepository;

    @Override
    public List<StoreDTO> findAllStores() {
        return storeRepository.findAll()
                .stream().map(Mappers::toDTO).toList();
    }

    @Override
    public StoreDTO createStore(StoreDTO newStore) {
        return null;
    }

    @Override
    public StoreDTO updateStore(Long idStore, StoreDTO store) {
        return null;
    }

    @Override
    public void deleteStore(Long idStore) {

    }
}
