package com.mcs.enums;

public enum ResultCodeEnum {
    TIMEOUT_EXCEPTION("-4", "业务超时"),
    OK("100", "成功"),
    SERVICE_ERROR("201", "服务器异常"),
    DEFAULT_ERROR("1000", "系统出错, 请联系管理员!"),
    ERROR("1001", "系统出错,错误未知!"),
    PARAM_ERROR("1002", "提交参数异常, 请联系管理员!"),
    SERVER_INNER_ERROR("1003", "请求错误，服务器内部发生异常！请联系管理员!"),
    SESSION_EXPIRE("1004", "未登录或会话已过期, 请重新登录!"),
    SUCCESS_BUT_EXIST_ERROR("5004", "操作成功但存在处理不成功记录"),
    ILLEGAL_OPERATE("1005", "您无权限操作该记录!"),
    NOT_BIND("1006", "微信没有绑定手机号码"),
    WX_ALREADY_BIND("1007", "已经绑定过了"),
    VERIFY_ERROR("1008", "验证失败");

    private String code;
    private String msg;

    private ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

