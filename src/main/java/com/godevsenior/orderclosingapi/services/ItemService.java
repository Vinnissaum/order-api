package com.godevsenior.orderclosingapi.services;

import com.godevsenior.orderclosingapi.entities.Item;
import com.godevsenior.orderclosingapi.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return repository.findAll();
    }
}
