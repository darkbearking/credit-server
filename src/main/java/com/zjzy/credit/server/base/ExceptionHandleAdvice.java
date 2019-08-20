package com.zjzy.credit.server.base;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjzy.credit.common.exception.BaseException;
import com.zjzy.credit.common.exception.BaseRuntimeException;
import com.zjzy.credit.common.model.ResultInfo;

@ControllerAdvice
public class ExceptionHandleAdvice {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandleAdvice.class);

    @ExceptionHandler
    @ResponseBody
    public ResultInfo<?> handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        logger.info("Restful Http请求发生异常...");

        if (e instanceof BaseRuntimeException || e instanceof BaseException) {
            return ResultInfo.errorMessage(e.getMessage());
        }
        // 兼容Preconditions.checkNotNull判断
        if (e instanceof NullPointerException && StringUtils.isNotEmpty(e.getMessage())) {
            return ResultInfo.errorMessage(e.getMessage());
        }

        // 把漏网的异常信息记入日志
        logger.error("Exception:{}", e.getMessage(), e);
        return ResultInfo.errorMessage("服务器内部错误");
    }

    /**
     * 输出json结果
     *
     * @param json     json串
     * @param response http返回对象
     */
    public void printToJson(String json, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json");
            response.setDateHeader("Expires", 0);
            PrintWriter out = response.getWriter();
            out.print(json);
            logger.debug(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("Error:" + e.getMessage(), e);
        }
    }
}
