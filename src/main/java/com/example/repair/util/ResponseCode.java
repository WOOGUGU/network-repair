package com.example.repair.util;

/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @author WOOGUGU
 */
public enum ResponseCode {
    //登录状态： 在线
    ON_LINE(0),
    //登录状态：离线
    OFF_LINE(1),
    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //未认证（签名错误）
    UNAUTHORIZED(401),
    //接口不存在
    NOT_FOUND(404),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500),
    //参数缺失
    ParamLost(301),
    //指标不存在
    IndexLost(300),
    //sql配置错误
    SqlConfigError(302),
    //没有指标查询权限
    hasNotAccess(303);

    public int value;

    ResponseCode(int value) {
        this.value = value;
    }

}
