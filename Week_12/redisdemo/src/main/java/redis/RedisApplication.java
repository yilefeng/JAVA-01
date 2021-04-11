package redis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication(scanBasePackages = "com.yilefeng.redis")
public class RedisApplication {

	public static void main(String[] args) {

		// C1.最简单demo
//		Jedis jedis = new Jedis("localhost", 6379);
//		System.out.println(jedis.info());
//		jedis.set("uptime", new Long(System.currentTimeMillis()).toString());
//		System.out.println(jedis.get("uptime"));

		// C2.基于sentinel和连接池的demo
//		Jedis sjedis = SentinelJedis.getJedis();
//		System.out.println(sjedis.info());
//		sjedis.set("uptime2", Long.toString(System.currentTimeMillis()));
//		System.out.println(sjedis.get("uptime2"));
//		SentinelJedis.close();

		// C3. 直接连接sentinel进行操作
//		Jedis jedisSentinel = new Jedis("localhost", 26380); // 连接到sentinel
//		List<Map<String, String>> masters = jedisSentinel.sentinelMasters();
//		System.out.println(JSON.toJSONString(masters));
//		List<Map<String, String>> slaves = jedisSentinel.sentinelSlaves("mymaster");
//		System.out.println(JSON.toJSONString(slaves));


		// 作业：
		// 1. 参考C2，实现基于Lettuce和Redission的Sentinel配置
		redissionSentinel();
		// 2. 实现springboot/spring data redis的sentinel配置
		// 3. 使用jedis命令，使用java代码手动切换 redis 主从
		// 	  Jedis jedis1 = new Jedis("localhost", 6379);
		//    jedis1.info...
		//    jedis1.set xxx...
		//	  Jedis jedis2 = new Jedis("localhost", 6380);
		//    jedis2.slaveof...
		//    jedis2.get xxx
		// jedisSlave();
		// 4. 使用C3的方式，使用java代码手动操作sentinel


		// C4. Redis Cluster
		// 作业：
		// 5.使用命令行配置Redis cluster:
		//  1) 以cluster方式启动redis-server
		//  2) 用meet，添加cluster节点，确认集群节点数目
		//  3) 分配槽位，确认分配成功
		//  4) 测试简单的get/set是否成功
		// 然后运行如下代码
// 		JedisCluster cluster = ClusterJedis.getJedisCluster();
//		for (int i = 0; i < 100; i++) {
//			cluster.set("cluster:" + i, "data:" + i);
//		}
//		System.out.println(cluster.get("cluster:92"));
//		ClusterJedis.close();

		//SpringApplication.run(RedisApplication.class, args);

	}

	private static void redissionSentinel() {
		// TODO: 后面的作业后续补充

	}

	private static void jedisSlave() {
		// 3. 使用jedis命令，使用java代码手动切换 redis 主从
		Jedis jedis1 = new Jedis("localhost", 6379);
		System.out.println(jedis1.info("Replication"));
//		jedis1.set("uptime", Long.toString(System.currentTimeMillis()));
		Jedis jedis2 = new Jedis("localhost",6380);
		System.out.println(jedis2.info("Replication"));
		Jedis jedis3 = new Jedis("localhost",6381);
		System.out.println(jedis3.info("Replication"));
		jedis2.slaveofNoOne();
		jedis1.slaveof("127.0.0.1",6380);
		jedis3.slaveof("127.0.0.1",6380);
//		System.out.println(jedis1.set("uptime", Long.toString(System.currentTimeMillis())));
//		System.out.println(jedis2.set("uptime", Long.toString(System.currentTimeMillis())));
		System.out.println(jedis2.info("Replication"));
	}

}
