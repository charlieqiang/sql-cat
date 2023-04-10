package cn.charlie.sqlcat.service;

import cn.charlie.sqlcat.entity.BaseInfo;
import cn.charlie.sqlcat.entity.BaseInfoParam;
import cn.charlie.sqlcat.interceptor.SqlIntercepterHandler;
import cn.charlie.sqlcat.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie
 * @date 4/10/2023 10:30 AM
 **/
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseMapper baseMapper;

    @Override
    public List<BaseInfo> queryById(Long baseId) {
        if (ObjectUtils.isEmpty(baseId)) {
            throw new RuntimeException("参数不能为空");
        }
        List<BaseInfo> baseInfoList = new ArrayList<>();
        try {
            SqlIntercepterHandler.open();
            List<BaseInfo> baseInfoTempList = baseMapper.queryById(baseId);
            if (!CollectionUtils.isEmpty(baseInfoTempList)) {
                baseInfoList.addAll(baseInfoTempList);
            }
        } finally {
            SqlIntercepterHandler.finishSql();
            SqlIntercepterHandler.finish();
        }
        return baseInfoList;
    }

    @Override
    public List<BaseInfo> queryByParam(BaseInfoParam baseInfoParam) {
        if (ObjectUtils.isEmpty(baseInfoParam)) {
            throw new RuntimeException("参数不能为空");
        }
        List<BaseInfo> baseInfoList = new ArrayList<>();
        try {
            SqlIntercepterHandler.open();
            List<BaseInfo> baseInfoTempList = baseMapper.queryByParam(baseInfoParam);
            if (!CollectionUtils.isEmpty(baseInfoTempList)) {
                baseInfoList.addAll(baseInfoTempList);
            }
        } finally {
            SqlIntercepterHandler.finishSql();
            SqlIntercepterHandler.finish();
        }
        return baseInfoList;
    }
}
