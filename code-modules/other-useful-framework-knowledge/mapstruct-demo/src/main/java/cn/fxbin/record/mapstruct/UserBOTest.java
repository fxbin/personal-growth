package cn.fxbin.record.mapstruct;

import cn.fxbin.record.mapstruct.bo.UserBO;
import cn.fxbin.record.mapstruct.convert.UserConvert;
import cn.fxbin.record.mapstruct.dataobject.UserDO;

/**
 * UserBOTest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 14:17
 */
public class UserBOTest {

    public static void main(String[] args) {
        // 创建 UserDO 对象
        UserDO userDO = new UserDO()
                .setId(1).setUsername("yudaoyuanma").setPassword("buzhidao");
        // <X> 进行转换
        UserBO userBO = UserConvert.INSTANCE.convert(userDO);
        System.out.println(userBO.getId());
        System.out.println(userBO.getUsername());
        System.out.println(userBO.getPassword());
    }

}
