package cn.fxbin.record.study.mapstruct.convert;

import cn.fxbin.record.study.mapstruct.bo.UserBO;
import cn.fxbin.record.study.mapstruct.bo.UserDetailBO;
import cn.fxbin.record.study.mapstruct.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * UserConvert
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/28 14:16
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserBO convert(UserDO userDO);

    @Mappings(
            @Mapping(source = "id", target = "userId")
    )
    UserDetailBO convertDetail(UserDO userDO);

}
