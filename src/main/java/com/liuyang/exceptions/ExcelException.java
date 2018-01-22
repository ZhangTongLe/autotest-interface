/*
 * Copyright (c) 2017. 郑州仁中和科技有限公司.保留所有权利.
 *                       http://www.rzhkj.com/
 *      郑州仁中和科技有限公司保留所有代码著作权.如有任何疑问请访问官方网站与我们联系.
 *      代码只针对特定客户使用，不得在未经允许或授权的情况下对外传播扩散.恶意传播者，法律后果自行承担.
 *      本代码仅用于郑州爱馨养老集团的爱馨养老综合管理系统.
 */
package com.liuyang.exceptions;

import com.alibaba.fastjson.JSON;

/**
 * excel处理基本异常类
 *
 * @author shangu 2017-08-03 17:46
 */

public class ExcelException extends BaseException {
    public ExcelException(ExceptionMessage exceptionMessage) {
        super("{message:\"" + exceptionMessage.message + "\",errorCode:\"" + exceptionMessage.errorCode + "\"}");
    }

    public ExcelException(ExceptionMessage exceptionMessage, Object... params) {
        super("{message:\"" + exceptionMessage.message + "\",errorCode:\"" + exceptionMessage.errorCode + "\",params:\"" + JSON.toJSONString(params) + "\"}");
    }

    public ExcelException(String message) {
        super(message);
    }


    public ExcelException(String message, Throwable cause) {
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
        NONEIMPORT("未导入数据", ""),
        NOSESSION("session不存在 ", ""),
        NOLOCULPATH("本地路径不存在", "");
        public String message;
        public String errorCode;

        ExceptionMessage(String message, String errorCode) {
            this.message = message;
            this.errorCode = errorCode;
        }
    }
}
