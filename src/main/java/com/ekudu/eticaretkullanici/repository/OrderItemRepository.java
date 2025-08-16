package com.ekudu.eticaretkullanici.repository;

import com.ekudu.eticaretkullanici.model.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity,Long> {

}
