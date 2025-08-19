package com.ekudu.eticaretkullanici.dto;


import com.ekudu.eticaretkullanici.model.OrderItemEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private List<OrderItemDto> orderItemList;




}
