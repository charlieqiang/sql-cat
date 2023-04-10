package cn.charlie.sqlcat.service;

import cn.charlie.sqlcat.entity.BaseInfo;
import cn.charlie.sqlcat.entity.BaseInfoParam;

import java.util.List;

/**
 * @author charlie
 * @date 4/10/2023 10:30 AM
 **/
public interface BaseService {

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
