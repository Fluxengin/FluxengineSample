package jp.co.fluxengine.example.plugin.effector;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.fluxengine.stateengine.annotation.Effector;
import jp.co.fluxengine.stateengine.annotation.Post;
import jp.co.fluxengine.stateengine.util.ReflectUtils;
import jp.co.fluxengine.stateengine.util.Serializer.KryoSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Effector("rule/性能検証ルール#redisデータ出力")
public class RedisEffector {

    private static final Logger log = LoggerFactory.getLogger(RedisEffector.class);

    @Post
    public void post() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

       Class clz =  ReflectUtils.getClass("jp.co.fluxengine.stateengine.persist.RedisDao");

       Field f = clz.getDeclaredField("jedisPool");

       f.setAccessible(true);

        JedisPool pool = (JedisPool) f.get(clz);
        try (Jedis jedis = pool.getResource()) {

            Set<byte[]> keySet = jedis.keys("*".getBytes());
            byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
            byte[][] values = jedis.mget(keys).toArray(new byte[keySet.size()][]);

            int index = 0;
            Map<String, Object> ret = com.google.common.collect.Maps.newHashMap();
            for (byte[] byts : values) {

                KryoSerializer ser = new KryoSerializer(HashMap.class);
                Map<String, Object> valueMap = ser.deserialize(byts);
                String key = new String(keys[index]);

                if (!key.endsWith("counter")) {
                    ret.put(key, valueMap);
                }
                index++;
            }

            LocalDateTime minupdatetime = null;
            LocalDateTime maxupdatetime = null;
            for (Entry<String, Object> entry : ret.entrySet()) {
                if (entry.getValue() instanceof HashMap) {
                    Map map = (Map) entry.getValue();
                    LocalDateTime lupdatetime = (LocalDateTime) map.get("updateTime");
                    if (minupdatetime == null) {
                        minupdatetime = lupdatetime;
                    } else {
                        if (lupdatetime.compareTo(minupdatetime) < 0) {
                            minupdatetime = lupdatetime;
                        }
                    }
                    if (maxupdatetime == null) {
                        maxupdatetime = lupdatetime;
                    } else {
                        if (maxupdatetime.compareTo(minupdatetime) > 0) {
                            maxupdatetime = lupdatetime;
                        }
                    }

                }
            }

            log.warn("最小時間：{},最大時間：{}", minupdatetime, maxupdatetime);

        }
    }

//    private JedisPool initConnectionPool() {
//        Properties props = new Properties();
//
//        try (InputStream in = RedisEffector.class.getResourceAsStream("/fluxengine.properties")) {
//            props.load(in);
//        } catch (IOException e) {
//            log.error("error in initConnectionPool", e);
//            throw new UncheckedIOException(e);
//        }
//
//        String host = props.getProperty("persister.memorystore.host");
//        int port = Integer.parseInt(props.getProperty("persister.memorystore.port"));
//
//        log.debug("Memorystore host = {}, port = {}", host, port);
//
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxWaitMillis(10000);
//
//        return new JedisPool(poolConfig, host, port, 10000);
//    }
}