package com.ekudu.eticaretkullanici;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ETicaretKullaniciApplication {

    public static void main(String[] args) {
        SpringApplication.run(ETicaretKullaniciApplication.class, args);
    }

}
