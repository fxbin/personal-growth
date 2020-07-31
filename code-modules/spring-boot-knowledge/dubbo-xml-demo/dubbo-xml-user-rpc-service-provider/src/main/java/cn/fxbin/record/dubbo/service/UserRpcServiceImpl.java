package cn.fxbin.record.dubbo.service;

import cn.fxbin.record.dubbo.api.UserRpcService;
import cn.fxbin.record.dubbo.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * UserRpcServiceImpl
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 18:39
 */
@Service
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
