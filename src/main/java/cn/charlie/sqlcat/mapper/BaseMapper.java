package cn.charlie.sqlcat.mapper;

import cn.charlie.sqlcat.entity.BaseInfo;
import cn.charlie.sqlcat.entity.BaseInfoParam;

import java.util.List;

/**
 * @author charlie
 * @date 3/23/2023 8:08 PM
 **/
public interface BaseMapper {

    /**
     * 查询
     *
     * @param baseId id
     * @return 结果集
     */
    List<BaseInfo> queryById(Long baseId);

    /**
     * 通过参数查询
     *
     * @param baseInfoParam 参数
     * @return 结果集
     */
    List<BaseInfo> queryByParam(BaseInfoParam baseInfoParam);
}
