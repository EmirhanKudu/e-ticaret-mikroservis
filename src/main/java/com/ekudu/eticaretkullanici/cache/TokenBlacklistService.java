package com.ekudu.eticaretkullanici.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenBlacklistService {

    private final RedisTemplate<String, String> redis;

    public TokenBlacklistService(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    /**
     * Çıkış yapan token'ı blacklist'e ekler
     * @param token JWT token stringi
     * @param ttl   Redis'te ne kadar süre saklanacağı
     */
    public void blacklistToken(String token, Duration ttl) {
        redis.opsForValue().set(token, "BLACKLISTED", ttl);
    }

    /** Token blacklist'te mi? */
    public boolean isBlacklisted(String token) {
        return redis.hasKey(token);
    }
}
