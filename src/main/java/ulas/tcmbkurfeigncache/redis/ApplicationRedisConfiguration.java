package ulas.tcmbkurfeigncache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;


@Configuration
public class ApplicationRedisConfiguration {

	@Autowired
	private ApplicationRedisProperties applicationRedisProperties;

	private GenericObjectPoolConfig getPoolConfig() {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		Pool pool = applicationRedisProperties.getPool();
		config.setMaxIdle(pool.getMaxIdle());
		config.setMinIdle(pool.getMinIdle());
		config.setMaxWaitMillis(pool.getMaxWait());
		config.setMaxTotal(pool.getMaxActive());
		return config;
	}

	private RedisStandaloneConfiguration redisStandaloneConfiguration() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setDatabase(applicationRedisProperties.getDatabase());
		redisStandaloneConfiguration.setHostName(applicationRedisProperties.getHost().trim());
		redisStandaloneConfiguration.setPort(applicationRedisProperties.getPort());
		if (!StringUtils.isEmpty(applicationRedisProperties.getPassword())) {
			redisStandaloneConfiguration.setPassword(RedisPassword.of(applicationRedisProperties.getPassword()));
		} else {
			redisStandaloneConfiguration.setPassword(RedisPassword.of(""));
		}

		return redisStandaloneConfiguration;
	}

	private JedisClientConfiguration jedisClientConfiguration() {
		JedisClientConfigurationBuilder jedisClientConfigurationBuilder = JedisClientConfiguration.builder();
		jedisClientConfigurationBuilder.usePooling().poolConfig(getPoolConfig());
		JedisClientConfiguration jCCB = jedisClientConfigurationBuilder.build();
		return jCCB;
	}

	@Bean(ApplicationRedisUtils.REDIS_CONNECTION_FACTORY)
	public RedisConnectionFactory applicationRedisConnectionFactory() {
		return new JedisConnectionFactory(redisStandaloneConfiguration(), jedisClientConfiguration());
	}

	@Bean(ApplicationRedisUtils.redisTemplate)
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(applicationRedisConnectionFactory());
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}

	@Bean(ApplicationRedisUtils.stringRedisTemplate)
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(applicationRedisConnectionFactory());
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}

}
