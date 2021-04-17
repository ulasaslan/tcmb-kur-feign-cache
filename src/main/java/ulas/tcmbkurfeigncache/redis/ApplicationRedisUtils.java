package ulas.tcmbkurfeigncache.redis;


public class ApplicationRedisUtils {

	/**
	 * @apiNote Bean name for Application Redis Factroy
	 */
	public static final String REDIS_CONNECTION_FACTORY = "applicationRedisConnectionFactory";

	/**
	 * @apiNote Bean name for redisTemplate
	 */
	public static final String redisTemplate = "redisTemplate";

	/**
	 * @apiNote Bean name for stringRedisTemplate
	 */
	public static final String stringRedisTemplate = "stringRedisTemplate";

	/**
	 * @apiNote cache KEY_SEPERATOR
	 */
	public static final String KEY_SEPERATOR = "::";

	public static class CacheBeans {
		/**
		 * @apiNote Bean name for Application Cache Manager
		 */
		public static final String CACHE_MANAGER = "cacheManager";
		/**
		 * @apiNote Bean name for Application Cache Manager with Transaction
		 */
		public static final String CACHE_MANAGER_TXN = "cacheManagerTXN";
		/**
		 * @apiNote Bean name for Application Redis Cache Key Generator
		 */
		public static final String KEY_GENERATOR = "keyGenerator";

	}
}
