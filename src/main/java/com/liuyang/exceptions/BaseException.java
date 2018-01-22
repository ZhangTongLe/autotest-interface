/*
 * Copyright (c) 2017. 郑州仁中和科技有限公司.保留所有权利.
 *                       http://www.rzhkj.com/
 *      郑州仁中和科技有限公司保留所有代码著作权.如有任何疑问请访问官方网站与我们联系.
 *      代码只针对特定客户使用，不得在未经允许或授权的情况下对外传播扩散.恶意传播者，法律后果自行承担.
 *      本代码仅用于河南乐醒网络科技的乐醒月付项目.
 */
package com.liuyang.exceptions;

import com.alibaba.fastjson.JSON;

/**
 * 基本异常类
 *
 * @author liukeqiang 2017-08-03 17:46
 */

public class BaseException extends RuntimeException {
    public BaseException(ExceptionMessage exceptionMessage) {
        super("{message:\"" + exceptionMessage.message + "\",errorCode:\"" + exceptionMessage.errorCode + "\"}");
    }

    public BaseException(ExceptionMessage exceptionMessage, Object... params) {
        super("{message:\"" + exceptionMessage.message + "\",errorCode:\"" + exceptionMessage.errorCode + "\",params:" + JSON.toJSONString(params) + "}");
    }

    public BaseException(String message) {
        super( message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Fills in the execution stack trace. This method records within this
     * {@code Throwable} object information about the current state of
     * the stack frames for the current thread.
     * <p>
     * <p>If the stack trace of this {@code Throwable} {@linkplain
     * Throwable#Throwable(String, Throwable, boolean, boolean) is not
     * writable}, calling this method has no effect.
     *
     * @return a reference to this {@code Throwable} instance.
     * @see Throwable#printStackTrace()
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

    /*异常信息定义*/
    public enum ExceptionMessage {
        HTTP_POST_EXCEPTION("http post请求异常", ""),
        HTTP_GET_EXCEPTION("http get请求异常", ""),
        PARAMISINVALID("无效", ""),
        ParamIsEmpty("参数为空错误", ""),
        SESSIONEMPTY("session不存在 ", ""),
        DATAISNOTEXIST("查询数据不存在", ""),
        DATADICT_IS_UNABLE("数据字典信息未启用", ""),
        CODENOTVALIDATE("验证码无效", ""),
        PHONEERROR("手机号格式不正确", ""),
        ALREADRUSING("已启用，无需重复操作", ""),
        ALREADYSTOP("已停用，无需重复操作", "");
        public String message;
        public String errorCode;

        ExceptionMessage(String message, String errorCode) {
            this.message = message;
            this.errorCode = errorCode;
        }
    }
}
