package com.jn.redisclient.core;

import com.jn.redisclient.core.key.RedisKeyWrapper;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisTemplate<V> extends org.springframework.data.redis.core.RedisTemplate<String, V> {
    private RedisKeyWrapper keyWrapper = new RedisKeyWrapper();

    public void setKeyWrapper(RedisKeyWrapper keyWrapper) {
        this.keyWrapper = keyWrapper;
    }

    private String wrapKey(String key) {
        return RedisKeyWrapper.wrap(keyWrapper, key);
    }

    @Override
    public <T> T execute(RedisScript<T> script, List<String> keys, Object... args) {
        return super.execute(script, RedisKeyWrapper.wrap(keyWrapper, keys), args);
    }

    @Override
    public <T> T execute(RedisScript<T> script, RedisSerializer<?> argsSerializer, RedisSerializer<T> resultSerializer,
                         List<String> keys, Object... args) {
        return super.execute(script, argsSerializer, resultSerializer, RedisKeyWrapper.wrap(keyWrapper, keys), args);
    }

    @Override
    public Boolean delete(String key) {
        return super.delete(wrapKey(key));
    }

    public Long delete(Collection<String> keys) {
        return super.delete(RedisKeyWrapper.wrap(keyWrapper, keys));
    }

    @Override
    public Boolean unlink(String key) {
        return super.unlink(wrapKey(key));
    }

    @Override
    public Long unlink(Collection<String> keys) {
        return super.unlink(RedisKeyWrapper.wrap(keyWrapper, keys));
    }

    @Override
    public Boolean hasKey(String key) {
        return super.hasKey(wrapKey(key));
    }

    @Override
    public Long countExistingKeys(Collection<String> keys) {
        return super.countExistingKeys(RedisKeyWrapper.wrap(keyWrapper, keys));
    }

    @Override
    public Boolean expire(String key, final long timeout, final TimeUnit unit) {
        return super.expire(wrapKey(key), timeout, unit);
    }

    @Override
    public Boolean expireAt(String key, final Date date) {
        return super.expireAt(wrapKey(key), date);
    }

    @Override
    public Long getExpire(String key) {
        return super.getExpire(wrapKey(key));
    }

    @Override
    public Long getExpire(String key, final TimeUnit timeUnit) {
        return super.getExpire(wrapKey(key), timeUnit);
    }

    @Override
    public Set<String> keys(String pattern) {
        return RedisKeyWrapper.unwrap(keyWrapper, super.keys(wrapKey(pattern)));
    }

    @Override
    public Boolean persist(String key) {
        return super.persist(wrapKey(key));
    }

    @Override
    public Boolean move(String key, final int dbIndex) {
        return super.move(wrapKey(key), dbIndex);
    }

    @Override
    public String randomKey() {
        return RedisKeyWrapper.unwrap(keyWrapper, super.randomKey());
    }

    @Override
    public void rename(String oldKey, String newKey) {
        super.rename(oldKey, newKey);
    }

    @Override
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return super.renameIfAbsent(oldKey, newKey);
    }

    @Override
    public DataType type(String key) {
        return super.type(wrapKey(key));
    }

    @Override
    public byte[] dump(String key) {
        return super.dump(wrapKey(key));
    }

    @Override
    public void restore(String key, final byte[] value, long timeToLive, TimeUnit unit, boolean replace) {
        super.restore(key, value, timeToLive, unit, replace);
    }

    @Override
    public void watch(String key) {
        super.watch(wrapKey(key));
    }

    @Override
    public void watch(Collection<String> keys) {
        super.watch(RedisKeyWrapper.wrap(keyWrapper, keys));
    }

    @Override
    public BoundGeoOperations<String, V> boundGeoOps(String key) {
        return super.boundGeoOps(wrapKey(key));
    }

    @Override
    public <HK, HV> BoundHashOperations<String, HK, HV> boundHashOps(String key) {
        return super.boundHashOps(wrapKey(key));
    }

    @Override
    public BoundListOperations<String, V> boundListOps(String key) {
        return super.boundListOps(wrapKey(key));
    }

    @Override
    public BoundSetOperations<String, V> boundSetOps(String key) {
        return super.boundSetOps(wrapKey(key));
    }

    @Override
    public <HK, HV> BoundStreamOperations<String, HK, HV> boundStreamOps(String key) {
        return super.boundStreamOps(wrapKey(key));
    }

    @Override
    public BoundValueOperations<String, V> boundValueOps(String key) {
        return super.boundValueOps(wrapKey(key));
    }

    @Override
    public BoundZSetOperations<String, V> boundZSetOps(String key) {
        return super.boundZSetOps(wrapKey(key));
    }


}
