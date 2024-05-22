package br.com.felipemira.web.test;
;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedisConnectionTest {

    @Test
    public void testConnection() {
        // Substitua com o seu host e porta
        String redisHost = "localhost";
        int redisPort = 6379;

        try (Jedis jedis = new Jedis(redisHost, redisPort)) {
            jedis.auth("UmVkaXMwMTAyMDMwNA==");
            jedis.set("testKey", "testValue");

            String value = jedis.get("testKey");
            assertTrue("testValue".equals(value));
        } catch (Exception e) {
            System.out.println(("Failed to connect to Redis: " + e.getMessage()));
        }
    }
}