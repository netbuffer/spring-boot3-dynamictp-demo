package cn.netbuffer.spring.boot3.dynamictp.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CalcComponent {

    @Async(value = "dtpExecutor1")
    public void calc1(Long value) {
        try {
            TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 3));
        } catch (InterruptedException e) {
            log.error("calc error:", e);
        }
        log.debug("calc1 some value={}", value);
    }

    @Async
    public void calc2(Long value) {
        try {
            TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 3));
        } catch (InterruptedException e) {
            log.error("calc error:", e);
        }
        log.debug("calc2 some value={}", value);
    }

}
