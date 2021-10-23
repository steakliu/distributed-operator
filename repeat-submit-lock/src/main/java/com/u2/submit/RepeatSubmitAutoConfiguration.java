package com.u2.submit;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.u2.submit.aspect.SubmitAspect;
import com.u2.submit.properties.RepeatSubmitProperties;
import com.u2.submit.service.impl.RepeatSubmitServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/10/17/14:27
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(RepeatSubmitProperties.class)
@ConditionalOnClass(RepeatSubmitServiceImpl.class)
@Import(SubmitAspect.class)
public class RepeatSubmitAutoConfiguration {


    final RepeatSubmitProperties repeatSubmitProperties;

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        template.setKeySerializer(stringRedisSerializer);

        template.setHashKeySerializer(stringRedisSerializer);

        template.setValueSerializer(jackson2JsonRedisSerializer);

        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RepeatSubmitServiceImpl repeatSubmitService(RedisConnectionFactory factory){
        return new RepeatSubmitServiceImpl(redisTemplate(factory));
    }

    public RepeatSubmitAutoConfiguration(RepeatSubmitProperties repeatSubmitProperties) {
        this.repeatSubmitProperties = repeatSubmitProperties;
    }
}
