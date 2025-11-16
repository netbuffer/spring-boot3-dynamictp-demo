package cn.netbuffer.spring.boot3.dynamictp.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.common.entity.NotifyPlatform;
import org.dromara.dynamictp.common.notifier.AbstractNotifier;

@Slf4j
public class Slf4jNotifier extends AbstractNotifier {

    public Slf4jNotifier() {
        log.info("init Slf4jNotifier");
    }

    @Override
    public String platform() {
        return "slf4j";
    }

    @Override
    protected void send0(NotifyPlatform platform, String content) {
        String[] receivers = platform.getReceivers().split(",");
        log.info("platform={} receivers={} content={}", platform, receivers, content);
    }

}