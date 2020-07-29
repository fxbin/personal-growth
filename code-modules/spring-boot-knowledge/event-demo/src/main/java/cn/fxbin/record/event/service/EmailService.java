package cn.fxbin.record.event.service;

import cn.fxbin.record.event.event.UserRegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * EmailService
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 17:10
 */
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
            logger.info("[onApplicationEvent][给用户({}) 发送邮件]", event.getUsername());
    }
}
