package com.ekudu.eticaretkullanici.cache;

import com.ekudu.eticaretkullanici.model.UserEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service

public class RefreshingService {



    private final RedisTemplate<String, String> redis;
    public  RefreshingService(RedisTemplate<String, String> redis) {

        this.redis = redis;
    }

//    public void addRefreshToken(Long userId, String refreshToken) {
//         String usId = userId.toString();
//        redis.opsForHash().put(usId,refreshToken);
//    }
}
