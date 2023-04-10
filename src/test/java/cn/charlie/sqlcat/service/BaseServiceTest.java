package cn.charlie.sqlcat.service;

import cn.charlie.sqlcat.entity.BaseInfo;
import cn.charlie.sqlcat.entity.BaseInfoParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author charlie
 * @date 4/9/2023 2:52 PM
 **/
@SpringBootTest
public class BaseServiceTest {
    @Autowired
    private BaseService baseService;

    @Test
    public void testQueryById() {
        Long id = 1089284975455461376L;
        List<BaseInfo> baseInfoList = baseService.queryById(id);

        if (CollectionUtils.isEmpty(baseInfoList)) {
            throw new RuntimeException("查无结果");
        }

        BaseInfo baseInfo = baseInfoList.get(0);
        String baseCode = "2";
        Assertions.assertEquals(baseInfo.getBaseCode(), baseCode);
    }

    @Test
    public void testQueryByParam() {
        Long id = 1089284975455461376L;
        String code = "2";
        BaseInfoParam baseInfoParam = new BaseInfoParam();
        baseInfoParam.setBaseId(id);
        baseInfoParam.setBaseCode(code);
        List<BaseInfo> baseInfoList = baseService.queryByParam(baseInfoParam);

        if (CollectionUtils.isEmpty(baseInfoList)) {
            throw new RuntimeException("查无结果");
        }

        BaseInfo baseInfo = baseInfoList.get(0);
        Assertions.assertEquals(baseInfo.getBaseCode(), code);
    }
}
