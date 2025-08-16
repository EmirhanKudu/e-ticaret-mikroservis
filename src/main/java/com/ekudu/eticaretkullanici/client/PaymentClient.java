package com.ekudu.eticaretkullanici.client;


import com.ekudu.eticaretkullanici.dto.PaymentRequestDto;
import com.ekudu.eticaretkullanici.dto.PaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "e-ticaret-odeme",url = "http://localhost:8081")
public interface PaymentClient {

    @PostMapping("/payment/pay")
    PaymentResponseDto processPayment(@RequestBody PaymentRequestDto paymentRequestDto);
}
