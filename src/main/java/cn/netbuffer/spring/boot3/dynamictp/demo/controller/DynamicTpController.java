package cn.netbuffer.spring.boot3.dynamictp.demo.controller;

import cn.netbuffer.spring.boot3.dynamictp.demo.component.CalcComponent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.core.DtpRegistry;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/dynamictp")
public class DynamicTpController {

    @Resource
    private Environment environment;
    @Resource
    private CalcComponent calcComponent;

    @GetMapping("/env")
    public String env(String key) {
        return environment.getProperty(key);
    }

    @GetMapping("executor")
    public String getExecutor(String name) {
        Executor executor = DtpRegistry.getExecutor(name);
        log.debug("get executor[{}]:{}", name, executor);
        return executor.getClass().getName();
    }

    @RequestMapping("execute")
    public long execute(String name) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Executor executor = DtpRegistry.getExecutor(name);
        executor.execute(() -> {
            log.debug("execute task in {}", name);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error("execute task error:", e);
            }
        });
        stopWatch.stop();
        return stopWatch.getTotalTimeMillis();
    }

    @RequestMapping("calc1")
    public void calc1(Long value) {
        log.debug("calc1 for value={}", value);
        calcComponent.calc1(value);
    }

    @RequestMapping("calc2")
    public void calc2(Long value) {
        log.debug("calc2 for value={}", value);
        calcComponent.calc2(value);
    }

}
