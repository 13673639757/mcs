package com.mcs.enums;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 状态枚举
 */
public enum StatusEnum {
    ENABLE("0", "正常"),
    DISABLE("-1", "删除"),
    ;


    private String code;
    private String msg;

    StatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static StatusEnum getInstance(String code) {
        if (null == code) return null;

        switch (code){
            case "enable":
                return StatusEnum.ENABLE;
            case "disable":
                return StatusEnum.DISABLE;
            default:
                return null;
        }

    }
    public static List<Map<String, Object>> getAllStatusType() {
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> map = null;
        for (StatusEnum en : values()) {
            map = new HashMap<String, Object>();
            map.put("code", en.getCode());
            map.put("msg", en.getMsg());
            list.add(map);
        }
        return list;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
