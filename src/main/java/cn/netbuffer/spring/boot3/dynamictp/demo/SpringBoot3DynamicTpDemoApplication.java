package cn.netbuffer.spring.boot3.dynamictp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringBoot3DynamicTpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3DynamicTpDemoApplication.class, args);
    }

}
