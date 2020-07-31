package cn.fxbin.record.dubbo.rpc.provider.service;

import cn.fxbin.record.dubbo.rpc.api.UserRpcService;
import cn.fxbin.record.dubbo.rpc.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

/**
 * UserRpcServiceImpl
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/31 10:09
 */
@DubboService(version = "${dubbo.provider.UserRpcService.version}")
public class UserRpcServiceImpl implements UserRpcService {
    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }
}
