package cn.fxbin.record.dubbo.rpc.api;


import cn.fxbin.record.dubbo.rpc.dto.UserDTO;

/**
 * UserRpcService
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/29 18:38
 */
public interface UserRpcService {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDTO get(Integer id);

}
