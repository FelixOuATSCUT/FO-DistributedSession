package com.example.demo.core;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author felix.ou
 */
@Slf4j
public class SessionRedisHelper {

    private final static String REDIS_HOST = "127.0.0.1";
    private final static int REDIS_PORT = 6379;

    //
    private FastJsonSerialization serialization = new FastJsonSerialization();
    private Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);

    public Map<String, Object> getSession(String sid) {
        Map<String, Object> session = new HashMap<String, Object>();
        try {
            byte[] bs = jedis.get(sid.getBytes());
            if (bs != null) {
                Object deserialize = serialization.deserialize(bs);
                session = (Map<String, Object>) deserialize;
            }
        } catch (Exception e) {
            log.error("Session存取异常", e);
        }
        return session;
    }

    public void saveSession(String sid, Map<String, Object> session) {
        try {
            byte[] bs = serialization.serialize(session);
            jedis.set(sid.getBytes(), bs);
            jedis.expire(sid.getBytes(), 3600);
        } catch (Exception e) {
            log.error("Session存取异常", e);
        }
    }

    public void removeSession(String sid) {
        try {
            jedis.del(sid.getBytes());
        } catch (Exception e) {
            log.error("Session存取异常", e);
        }
    }
}
