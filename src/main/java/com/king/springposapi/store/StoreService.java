package com.king.springposapi.store;

import com.king.springposapi.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreDTOMapper storeDTOMapper;

    private final ObjectValidator objectValidator;

    public List<StoreDTO> list(){
        return storeRepository.findAll()
                .stream()
                .map(storeDTOMapper)
                .toList();
    }

    public Optional<StoreDTO> getStore(UUID id){
        Optional<Store> store = storeRepository.findById(id);
        return store
                .stream()
                .map(storeDTOMapper)
                .findFirst();
    }

    public Store create(@NotNull("This field is required") StoreCreateRequest request){
        Store store = new Store(
                request.name(),
                request.location()
        );
        return storeRepository.save(store);
    }

    public ResponseEntity<StoreDTO> update(StoreUpdateRequest request, UUID id){
        var store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));
        store.setName(request.name());
        store.setStatus(request.status());
        store.setLocation(request.location());

        objectValidator.validate(store);

        storeRepository.save(store);

        return ResponseEntity.ok(storeDTOMapper.apply(store));
    }

    public void delete(UUID id){
        var store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Store not found"));
        storeRepository.deleteById(id);
    }
}
