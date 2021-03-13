package com.intellie.common.entity.system;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/25 17:28
 *
 * 公用的标识符
 */
public class CacheTag {
    /**
     *登录短信验证码的标识符
     * */
    public final static String VERIFY_TOKEN = "VERIFY_TOKEN";

    /**
     * 下一过滤器是否放行
     */
    public final static String NEXT_INTERCEPT = "NEXT_INTERCEPT";

    /**
     * 网关为请求添加的身份识别
     */
    public final static String GATEWAY_TOKEN = "GATEWAY_TOKEN";
    /**
     * 用户信息缓存
     */
    public final static String SERVER_LOGIN_TOKEN = "LOGIN_SUCCESS_";
    /**
     * 登录信息客户端缓存
     */
    public final static String CLIENT_LOGIN_TOKEN = "INTELLIEUSER";
    /**
     * 私钥缓存
     */
    public final static String PRIVATE = "PRIVATE_KEY";
    /**
     * 公钥缓存
     */
    public final static String PUBLIC = "PUBLIC_KEY";
    /**
     * API访问的token列表 名称
     */
    public final static String API_LIST_NAME = "API_LIST_NAME";
    /**
     * 登录用户的id列表 名称
     */
    public final static String LOGIN_STATUS = "LOGIN_STATUS_";
}
