package cn.netbuffer.spring.boot3.dynamictp.demo.config;

import org.dromara.dynamictp.spring.annotation.EnableDynamicTp;
import org.dromara.dynamictp.spring.support.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDynamicTp
@PropertySource(value = "classpath:/dynamic-tp.yml", factory = YamlPropertySourceFactory.class)
public class DynamicTpConfig {
}
