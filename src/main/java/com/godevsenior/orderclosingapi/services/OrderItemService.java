package com.godevsenior.orderclosingapi.services;

import com.godevsenior.orderclosingapi.dto.OrderItemDTO;
import com.godevsenior.orderclosingapi.entities.Item;
import com.godevsenior.orderclosingapi.entities.Order;
import com.godevsenior.orderclosingapi.entities.OrderItem;
import com.godevsenior.orderclosingapi.repositories.ItemRepository;
import com.godevsenior.orderclosingapi.repositories.OrderItemRepository;
import com.godevsenior.orderclosingapi.repositories.OrderRepository;
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
import java.util.UUID;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<OrderItemDTO> findAll(UUID id) {
        List<OrderItem> obj = repository.findAllByOrderId(id);

        return obj.stream().map(OrderItemDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public OrderItemDTO findById(UUID id) {
        Optional<OrderItem> obj = repository.findById(id);
        OrderItem entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new OrderItemDTO(entity);
    }

    @Transactional
    public OrderItemDTO insert(UUID id, OrderItemDTO dto) {
        OrderItem entity = new OrderItem();
        Order orderEntity = orderRepository.getReferenceById(id);
        Item itemEntity = itemRepository.getReferenceById(dto.getItemId());
        entity.setOrder(orderEntity);
        entity.setItem(itemEntity);
        entity.setOrderId(orderEntity.getId());
        entity.setItemId(dto.getItemId());
        entity.setQuantity(dto.getQuantity());
        entity = repository.save(entity);

        return new OrderItemDTO(entity);
    }

    @Transactional
    public OrderItemDTO update(UUID id, OrderItemDTO dto) {
        try {
            OrderItem entity = repository.getReferenceById(id);
            entity.setItemId(dto.getItemId());
            entity.setQuantity(dto.getQuantity());
            entity = repository.save(entity);
            return new OrderItemDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
