/*
 * Copyright (c) 2017. 郑州三楂红科技有限公司.保留所有权利.
 *          郑州三楂红科技有限公司保留所有代码著作权.如有任何疑问请访问官方网站与我们联系.
 *      代码只针对特定业务使用，不得在未经允许或授权的情况下对外传播扩散.恶意传播者，法律后果自行承担.
 *      本代码仅用于三楂红商城系统.
 */
package com.liuyang.utils;

import com.liuyang.exceptions.BaseException;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * HTTP工具类，获取用户真实IP等信息
 *
 * @author CCW
 *         2017-03-31
 */

public class HttpUtil {

    private static final MediaType FORM_MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";


    /**
     * 获取用户真实IP
     * <p>
     * 2017-03-31
     *
     * @param request
     * @return IP
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获得POST请求结果
     * @param url
     * @param params
     * @return String
     */
    public static String httpPostResult(String url, Map<String, Object> params) {
        Response response = httpPost(url, params, null);
        if (response == null) throw new BaseException(BaseException.ExceptionMessage.HTTP_POST_EXCEPTION, url);
        try {
            // 返回请求结果
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(BaseException.ExceptionMessage.HTTP_POST_EXCEPTION, url);
        }
    }

    /**
     * 发送http请求
     *
     * @see {@link @param     url} 请求地址
     * @param params 参数
     * @return
     */
    public static Response httpPost(String url, Map<String, Object> params) {
        return httpPost(url, params, null);
    }

    /**
     * 发送http请求 Post
     *
     * @see {@link @param     url} 请求地址
     * @param params 参数
     * @param mediaType 允许为空
     * @return
     */
    public static Response httpPost(String url, Map<String, Object> params, MediaType mediaType) {
        return http(url, params, HttpUtil.POST, mediaType);
    }

    /**
     * 获得请求结果 Get
     * @param url
     * @param params
     * @return String
     */
    public static String httpGetResult(String url, Map<String, Object> params) {
        Response response = httpGet(url, params);
        if (response == null) throw new BaseException(BaseException.ExceptionMessage.HTTP_GET_EXCEPTION, url);
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(BaseException.ExceptionMessage.HTTP_GET_EXCEPTION, url);
        }
    }

    /**
     * 发送http请求 Get
     *
     * @see {@link @param     url} 请求地址
     * @param params 参数
     * @return
     */
    public static Response httpGet(String url, Map<String, Object> params) {
        return httpGet(url, params, null);
    }

    /**
     * 发送http请求 Get
     *
     * @see {@link @param     url} 请求地址
     * @param params 参数
     * @param mediaType 允许为空
     * @return
     */
    public static Response httpGet(String url, Map<String, Object> params, MediaType mediaType) {
        return http(url, params, HttpUtil.GET, mediaType);
    }

    /**
     * 获取http请求
     * @param url       请求地址
     * @param params    请求参数
     * @param type      请求类型
     * @param mediaType 媒体类型
     * @param cookie    写入的cookie
     * @return
     */
    public static Response http(String url, Map<String, Object> params, String type, MediaType mediaType, String cookie) {
        if (mediaType == null) {
            mediaType = FORM_MEDIA_TYPE;
        }
        if (type == null) {
            type = "POST";
        }
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request.Builder builder = new Request.Builder();
        Request request = null;
        if (type.equalsIgnoreCase(HttpUtil.POST)) {
            RequestBody requestBody = RequestBody.create(mediaType, genParams(params));
            builder = builder.url(url).post(requestBody);
        } else if (type.equalsIgnoreCase(HttpUtil.GET)) {
            if (params != null && params.size() > 0) {
                url += "?" + genParams(params);
            }
            builder = builder.url(url).get();
        } else if (type.equalsIgnoreCase(HttpUtil.PUT)) {
            RequestBody requestBody = RequestBody.create(mediaType, genParams(params));
            builder = builder.url(url).put(requestBody);
        } else if (type.equalsIgnoreCase(HttpUtil.DELETE)) {
            if (params != null && params.size() > 0) {
                RequestBody requestBody = RequestBody.create(mediaType, genParams(params));
                builder = builder.url(url).delete(requestBody);
            } else {
                builder = builder.url(url).delete();
            }
        }
        //在头中加入cookie
        if (StringUtils.isNotBlank(cookie)) {
            builder.addHeader("Cookie", cookie);
        }
        request = builder.build();
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Response http(String url, Map<String, Object> params, String type) {
        return http(url, params, type, null, null);
    }

    public static Response http(String url, Map<String, Object> params, String type, MediaType mediaType) {
        return http(url, params, type, mediaType, null);
    }

    /**
     * 获取http请求结果并转换成字符串
     * @param url
     * @param params
     * @param type
     * @param mediaType
     * @param cookie
     * @return
     */
    public static String httpResult(String url, Map<String, Object> params, String type, MediaType mediaType, String cookie) {
        Response response = http(url, params, type, mediaType, cookie);
        if (response == null) throw new BaseException(BaseException.ExceptionMessage.HTTP_POST_EXCEPTION, url);
        try {
            // 返回请求结果
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(BaseException.ExceptionMessage.HTTP_POST_EXCEPTION, url);
        }
    }

    public static String httpResult(String url, Map<String, Object> params, String type, MediaType mediaType) {
        return httpResult(url, params, type, mediaType, null);
    }

    public static String httpResult(String url, Map<String, Object> params, String type) {
        return httpResult(url, params, type, null, null);
    }



    /**
     * 根据参数map生成参数字符创
     *
     * @return 参数字符串
     */
    private static String genParams(Map<String, Object> params) {
        StringBuilder str = new StringBuilder();
        for (String s : params.keySet()) {
            str.append("&").append(s).append("=").append(params.get(s).toString());
        }
        str = new StringBuilder(str.substring(1));
        return str.toString();
    }

    public static Response httpTestPost(String url, Map<String, Object> params) {
        RequestBody requestBody = RequestBody.create(FORM_MEDIA_TYPE, genParams(params));

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = null;
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(requestBody);


        request = builder.build();

        List<Cookie> cookies = client.cookieJar().loadForRequest(request.url());
        for (Cookie cookie : cookies) {
            System.out.println(cookie.name() + " = " + cookie.value());
        }
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
