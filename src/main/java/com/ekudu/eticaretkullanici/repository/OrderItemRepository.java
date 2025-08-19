package com.ekudu.eticaretkullanici.repository;

import com.ekudu.eticaretkullanici.model.OrderEntity;
import com.ekudu.eticaretkullanici.model.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity,Long> {
    public List<OrderItemEntity> findByOrderEntity(OrderEntity orderEntity);

}
