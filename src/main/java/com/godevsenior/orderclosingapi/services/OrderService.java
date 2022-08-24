package com.godevsenior.orderclosingapi.services;

import com.godevsenior.orderclosingapi.dto.OrderCloseDTO;
import com.godevsenior.orderclosingapi.dto.OrderDTO;
import com.godevsenior.orderclosingapi.entities.Order;
import com.godevsenior.orderclosingapi.entities.OrderItem;
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

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> orders = repository.findAll();

        return orders.stream().map(OrderDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        Order entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new OrderDTO(entity);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order entity = new Order();
        entity.setNumber(dto.getNumber());
        entity.setPercentageDiscount(dto.getPercentageDiscount());
        entity = repository.save(entity);

        return new OrderDTO(entity);
    }

    @Transactional
    public OrderDTO update(Long id, OrderDTO dto) {
        try {
            Order entity = repository.getReferenceById(id);
            entity.setNumber(dto.getNumber());
            entity.setPercentageDiscount(dto.getPercentageDiscount());
            entity.setTotalValue(dto.getTotalValue());
            entity = repository.save(entity);
            return new OrderDTO(entity);
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

    @Transactional
    public OrderCloseDTO closeOrder(Long id, OrderDTO dto) {
        Optional<Order> obj = repository.findById(id);
        Order entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(id);
        entity.setPercentageDiscount(dto.getPercentageDiscount());
        entity = repository.save(entity);

        return new OrderCloseDTO(entity, orderItems);
    }
}
