package com.godevsenior.orderclosingapi.services;

import com.godevsenior.orderclosingapi.dto.ItemDTO;
import com.godevsenior.orderclosingapi.entities.Item;
import com.godevsenior.orderclosingapi.repositories.ItemRepository;
import com.godevsenior.orderclosingapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Transactional(readOnly = true)
    public List<ItemDTO> findAll() {
        List<Item> list = repository.findAll();

        return list.stream().map(ItemDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ItemDTO findById(Long id) {
        Optional<Item> obj = repository.findById(id);
        Item entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new ItemDTO(entity);
    }
}
