package cn.charlie.sqlcat.entity;

import java.io.Serializable;

/**
 * @author charlie
 * @date 3/23/2023 9:52 PM
 **/
public class BaseInfoParam implements Serializable {
    private static final long serialVersionUID = 4513618728649653465L;
    /**
     * id
     */
    private Long baseId;

    /**
     * 编号
     */
    private String baseCode;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
}
