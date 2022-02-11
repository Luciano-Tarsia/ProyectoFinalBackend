package com.coderhouse.repository;

import com.coderhouse.model.UserRedis;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    public static final String MENSAJE_KEY = "MENSAJE";

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public RedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(UserRedis userRedis){
        hashOperations.put(MENSAJE_KEY, userRedis.getId(), userRedis);
    }

    public UserRedis findById(String id){
        return (UserRedis) hashOperations.get(MENSAJE_KEY, id);
    }

    public void update(UserRedis userRedis){
        save(userRedis);
    }

}
