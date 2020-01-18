
package com.kingleadsw.ysm.caches.config;

import com.kingleadsw.ysm.caches.RedisUtil;
import com.kingleadsw.ysm.utils.Asserts;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 配置类
 *
 * @author zhoujie
 *
 *
 */
@Configuration
@Log4j2
public class RedisConfiguration  {

    @Value("${redis.hostName}")
    private  String hostName;

    @Value("${redis.maxIdle}")
    private  Integer maxIdle;

   @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private  Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private  Integer minEvictableIdleTimeMillis ;

    @Value("${redis.numTestsPerEvictionRun}")
    private  Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private  long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private  boolean testOnBorrow ;

    @Value("${redis.testWhileIdle}")
    private  boolean testWhileIdle;

    @Value("${redis.password}")
    private  String  password;


    /**
     * JedisPoolConfig 连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxIdle(maxIdle);

        jedisPoolConfig.setMaxTotal(maxTotal);

        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);

        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        jedisPoolConfig.setTestOnBorrow(testOnBorrow);

        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        log.info("=============================jedis 建立连接=================================");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //连接池
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        //IP地址
        jedisConnectionFactory.setHostName(hostName);
        if(Asserts.notNull(password)){
            jedisConnectionFactory.setPassword(password);
        }

        jedisConnectionFactory.setPort(6379);
        //客户端超时时间单位是毫秒
        jedisConnectionFactory.setTimeout(5000);
        log.info("=============================jedis 建立连接结束=================================");
        return jedisConnectionFactory;
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }
    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
    /**
     * 注入封装RedisTemplate
     * @Title: redisUtil
     * @return RedisUtil
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }


}
