package com.ekudu.eticaretkullanici.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartCacheService {

    private final RedisTemplate<String, String> redis;

    public CartCacheService(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }


    public void addToCart(Long id, String productId, int qty) {
        String key = "cart:" + id;
        redis.opsForHash().put(key, productId, String.valueOf(qty));
    }

    public Map<Object, Object> getCart(Long id) {
        return redis.opsForHash().entries("cart:" + id);
    }

    public void clearCart(Long id) {
        redis.delete("cart:" + id);
    }
}
