package spring.web.common.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    private static final Log log = LogFactory.getLog(RedisTemplate.class);

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    private static final long FOREVER = 0;
    private static final long ONE_SECOND = 1;
    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 60 * 60;
    private static final long ONE_DAY = 24 * 60 * 60;
    private static final long ONE_WEEK = 7 * 24 * 60 * 60;

    public void del(String key){
        redisTemplate.delete(key);
    }

    public synchronized void setString(String key, String value, long expireTime){
        stringRedisTemplate.opsForValue().set(key, value);
        if (expireTime > 0) {
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    public synchronized void setString(String key, String value, Date expireDate){
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expireAt(key, expireDate);
    }

    public synchronized void setObject(String key, Object value, long expireTime){
        redisTemplate.opsForValue().set(key, value);
        if(expireTime > 0){
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    public synchronized void setObject(String key, Object value, Date expireDate){
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expireAt(key, expireDate);
    }

    public synchronized <D, T> void setMap(String key, Map<D, T> map, long expireTime){
        redisTemplate.opsForHash().getOperations().delete(key);
        redisTemplate.opsForHash().putAll(key, map);
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    public synchronized <D, T> void setMap(String key, Map<D, T> map, Date expireDate){
        redisTemplate.opsForHash().getOperations().delete(key);
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expireAt(key, expireDate);
    }

    public synchronized <V> void setList(String key, List<V> list, long expireTime) {
        redisTemplate.opsForList().getOperations().delete(key);
        redisTemplate.opsForList().rightPushAll(key, list);
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    public synchronized <V> void setList(String key, List<V> list, Date expireDate) {
        redisTemplate.opsForList().getOperations().delete(key);
        redisTemplate.opsForList().rightPushAll(key, list);
        redisTemplate.expireAt(key, expireDate);
    }

    public String getString(String key){
        return stringRedisTemplate.boundValueOps(key).get();
    }

    public <T> T getObject(String key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    public <D, T> Map<D, T> getMap(String key){
        BoundHashOperations<String, D, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    public <V> List<V> getList( String key) {
        if (StringUtils.hasText(key)) {
            Boolean b = redisTemplate.hasKey(key);
            if (b != null && b) {
                return redisTemplate.opsForList().range(key, 0, -1);
            }
        }
        return null;
    }

    /**
     * 资源锁，排队等待，超时则获取锁失败
     * @param lockKey
     * @param timeout
     * @return
     */
    public synchronized boolean tryLock(String lockKey, long timeout) {
        log.debug(String.format("tryLock lockKey:%s timeout:%s", lockKey, timeout));
        try {
            long tryTimeout = TimeUnit.SECONDS.toMillis(timeout);
            while (tryTimeout >= 0) {
                if (setNX(lockKey)) {
                    redisTemplate.expire(lockKey, timeout, TimeUnit.SECONDS);
                    return true;
                }
                long sleepTime = (long) (Math.random()*1000);
                tryTimeout -= sleepTime;
                log.info(String.format("getLock reTry lockKey:%s timeout:%s", lockKey, tryTimeout));
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException e) {
            log.error(String.format("getLock error lockKey:%s error:%s", lockKey, e.getMessage()), e);
        }
        return false;
    }

    /**
     * 原子锁，仅在第一次设置值时返回真，记得使用完后删除key
     * @param key
     * @return
     */
    public synchronized boolean setNX(final String key) {
        Object obj = redisTemplate.execute((RedisConnection connection)->{
            StringRedisSerializer serializer = new StringRedisSerializer();
            byte[] bytes = serializer.serialize(key);
            if (Objects.nonNull(bytes)) bytes = new byte[0];
            Boolean success = connection.setNX(bytes, bytes);
            connection.close();
            return success;
        });
        if (obj == null){
            log.info(String.format("setNX error, key:%s", key));
            return false;
        }
        return (Boolean) obj;
    }

    /**
     * 加减法，过期清零
     * @param key
     * @param expireTime
     * @return
     */
    public synchronized long increment(String key, long value, long expireTime){
        Long v = redisTemplate.opsForValue().increment(key, value);
        if (Objects.nonNull(v) && v == value) { // 第一次赋值
            if (expireTime > 0) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
        }
        return Objects.nonNull(v) ?  v : 0;
    }

    /**
     * 加减法，过期清零
     * @param key
     * @param expireDate
     * @return
     */
    public synchronized long increment(String key, long value, Date expireDate){
        Long v = redisTemplate.opsForValue().increment(key, value);
        if (Objects.nonNull(v) && v == value) {
            redisTemplate.expireAt(key, expireDate);
        }
        return Objects.nonNull(v) ? v : 0;
    }

    /**
     * 加减法，过期清零
     * @param key
     * @param expireTime
     * @return
     */
    public synchronized double increment(String key, double value, long expireTime){
        Double v = redisTemplate.opsForValue().increment(key, value);
        if (Objects.nonNull(v) && v == value) { // 第一次赋值
            if (expireTime > 0) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
        }
        return Objects.nonNull(v) ? v : 0;
    }

    /**
     * 加减法，过期清零
     * @param key
     * @param expireDate
     * @return
     */
    public synchronized double increment(String key, double value, Date expireDate){
        Double v = redisTemplate.opsForValue().increment(key, value);
        if (Objects.nonNull(v) && v == value) {
            redisTemplate.expireAt(key, expireDate);
        }
        return Objects.nonNull(v) ? v : 0;
    }


}

