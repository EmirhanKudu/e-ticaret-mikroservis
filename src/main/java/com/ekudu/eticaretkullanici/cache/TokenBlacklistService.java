package com.ekudu.eticaretkullanici.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class TokenBlacklistService {

    private final RedisTemplate<String, String> redis;

    public TokenBlacklistService(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    String key = "blacklist";
    public void addBlackListRefreshToken(Long userId ,String refreshToken) {
        redis.opsForHash().put(key, refreshToken ,userId);
    }

    public boolean isBlacklisted(String refreshToken) {
        Map<Object,Object> map = redis.opsForHash().entries(key);
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            if(entry.getKey().toString().equals(refreshToken)){
                return true;
            }


        }

        return false;
    }
}
