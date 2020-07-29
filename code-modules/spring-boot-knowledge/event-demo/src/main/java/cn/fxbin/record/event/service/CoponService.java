package cn.fxbin.record.event.service;

import cn.fxbin.record.event.event.UserRegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * CoponService
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 17:10
 */
@Service
public class CoponService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    public void addCoupon(UserRegisterEvent event) {
        logger.info("[addCoupon][给用户({}) 发放优惠劵]", event.getUsername());
    }

}
