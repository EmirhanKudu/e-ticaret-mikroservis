package com.ekudu.eticaretkullanici.repository;

import com.ekudu.eticaretkullanici.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
