package com.ekudu.eticaretkullanici.cache;

import com.ekudu.eticaretkullanici.model.UserEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service

public class RefreshingService {

    String key = "refresh_token:";

    private final RedisTemplate<String, String> redis;
    public  RefreshingService(RedisTemplate<String, String> redis) {

        this.redis = redis;
    }

    public void addRefreshToken(Long id, String refreshToken) {

        redis.opsForHash().put(key,refreshToken,id);
    }

    public Long getIdByRefreshToken(String refreshToken) {
        Long usId = Long.parseLong(redis.opsForHash().get(key,refreshToken).toString());
        return usId;
    }

    public String getRefreshTokenById(Long id) {
        String refreshToken = null;
        Map<Object, Object> refreshMap = redis.opsForHash().entries(key);
        for (Map.Entry<Object, Object> entry : refreshMap.entrySet()) {
            if (entry.getValue().equals(id)) {
                refreshToken = entry.getKey().toString();
            }
        }
        return refreshToken;


    }

}
