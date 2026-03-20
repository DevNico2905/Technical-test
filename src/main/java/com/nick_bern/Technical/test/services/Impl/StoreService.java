package com.nick_bern.Technical.test.services.Impl;

import com.nick_bern.Technical.test.dtos.StoreDTO;
import com.nick_bern.Technical.test.exceptions.NotFoundException;
import com.nick_bern.Technical.test.mappers.Mappers;
import com.nick_bern.Technical.test.models.Store;
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
    public StoreDTO createStore(StoreDTO store) {

        Store newStore = Store.builder()
                .id(store.getId())
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .build();

        return Mappers.toDTO(storeRepository.save(newStore));
    }

    @Override
    public StoreDTO updateStore(Long idStore, StoreDTO store) {
        Store updatedStore = storeRepository.findById(idStore)
                .orElseThrow(() -> new NotFoundException("Store with id: " + idStore + " not found"));

        updatedStore.setStoreName(store.getStoreName());
        updatedStore.setStoreAddress(store.getStoreAddress());

        return Mappers.toDTO(storeRepository.save(updatedStore));
    }

    @Override
    public void deleteStore(Long idStore) {
        if (!storeRepository.existsById(idStore)){
            throw new NotFoundException("Store with id: " + idStore + " not found!");
        } else {
            storeRepository.deleteById(idStore);
        }
    }
}
