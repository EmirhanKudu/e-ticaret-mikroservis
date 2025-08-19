package com.ekudu.eticaretkullanici.service;

import com.ekudu.eticaretkullanici.dto.CardDto;
import com.ekudu.eticaretkullanici.dto.OrderDto;
import com.ekudu.eticaretkullanici.dto.OrderResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderService {


   OrderResponseDto doPayment(CardDto card);

   OrderDto getOrder(Long orderId);
}
