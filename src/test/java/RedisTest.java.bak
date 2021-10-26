import com.zx5435.echo.iothub.IotHubApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Collections;

@SpringBootTest(classes = IotHubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RedisTest {

    @Resource
    StringRedisTemplate redisTemplate;

    @Test
    void asdf() {
        redisTemplate.opsForValue().set("a", "b123");

        StreamOperations<String, String, String> stream = redisTemplate.opsForStream();

        stream.add("x:topic:test", Collections.singletonMap("a", "123"));
    }

}
