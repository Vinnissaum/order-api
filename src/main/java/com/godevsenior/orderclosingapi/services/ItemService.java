package com.godevsenior.orderclosingapi.services;

import com.godevsenior.orderclosingapi.dto.ItemDTO;
import com.godevsenior.orderclosingapi.entities.Item;
import com.godevsenior.orderclosingapi.repositories.ItemRepository;
import com.godevsenior.orderclosingapi.services.exceptions.DatabaseException;
import com.godevsenior.orderclosingapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    @Transactional
    public ItemDTO insert(ItemDTO dto) {
        Item entity = new Item();
        entity.setDescription(dto.getDescription());
        entity.setCost(dto.getCost());
        entity.setType(dto.getType());
        entity = repository.save(entity);

        return new ItemDTO(entity);
    }

    @Transactional
    public ItemDTO update(Long id, ItemDTO dto) {
        try {
            Item entity = repository.getReferenceById(id);
            entity.setDescription(dto.getDescription());
            entity.setCost(dto.getCost());
            entity.setType(dto.getType());
            entity = repository.save(entity);
            return new ItemDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
