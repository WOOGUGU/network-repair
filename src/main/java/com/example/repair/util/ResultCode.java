package com.example.repair.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回码
 *
 * @author WOOGUGU
 */
public class ResultCode {
    /**
     * 1. code : 响应业务状态
     * 2. message: 响应消息
     * 3. result : 响应中的数据
     */

    public static JSONObject requestSucesse() {
        JSONObject json = new JSONObject();
        json.put("code", ResponseCode.SUCCESS.value);
        json.put("message", "发现用户");
        json.put("data", "1");
        return json;
    }

    public static JSONObject requestFail() {
        JSONObject json = new JSONObject();
        json.put("code", ResponseCode.IndexLost.value);
        json.put("message", "未发现用户");
        json.put("data", "0");
        return json;
    }

    public static JSONObject getJson(Object data) {
        JSONObject json = new JSONObject();
        json.put("code", ResponseCode.SUCCESS.value);
        json.put("message", "请求数据成功");
        json.put("data", data);
        return json;
    }

    public static JSONObject getJson(Object data, String message) {
        JSONObject json = new JSONObject();
        json.put("code", ResponseCode.SUCCESS.value);
        json.put("message", message);
        json.put("data", data);
        return json;
    }

    public static JSONObject getJson(int code, Object data, String message) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
        json.put("data", data);
        return json;
    }

    public static JSONObject Fail(String message) {
        JSONObject json = new JSONObject();
        json.put("code", ResponseCode.FAIL.value);
        json.put("message", message);
        json.put("data", 0);
        return json;
    }

    /**
     * 处理返回的json
     *
     * @param result 处理后的结构化数据
     * @param total
     * @return
     */
    public static JSONObject getJsonForLog(Object result, int total) {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");
        json.put("count", total);
        json.put("data", result);
        return json;
    }
}