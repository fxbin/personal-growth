package cn.fxbin.record.study.mapstruct;

import cn.fxbin.record.study.mapstruct.bo.UserDetailBO;
import cn.fxbin.record.study.mapstruct.convert.UserConvert;
import cn.fxbin.record.study.mapstruct.dataobject.UserDO;

/**
 * UserDetailBOTest
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 14:29
 */
public class UserDetailBOTest {

    public static void main(String[] args) {
        // 创建 UserDO 对象
        UserDO userDO = new UserDO()
                .setId(1).setUsername("yudaoyuanma").setPassword("buzhidao");
        // 进行转换
        UserDetailBO userDetailBO = UserConvert.INSTANCE.convertDetail(userDO);
        System.out.println(userDetailBO.getUserId());
    }

}
