package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.SessionException;
import com.example.demo.exception.SessionExceptionCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author felix.ou
 */
@RestController
public class TestController {

    @RequestMapping(value = "/testSet", method = {RequestMethod.GET, RequestMethod.POST})
    public String testSet(HttpServletRequest request) throws Exception {

        String text = request.getParameter("text");
        if (StringUtils.isEmpty(text)) {
            text = "default";
        }
        try {
            request.getSession().setAttribute("test", text);
        } catch (Exception e) {
            throw new SessionException(SessionExceptionCode.ER_REDIS);
        }
        return text;
    }

    @RequestMapping(value = "/testGet", method = {RequestMethod.GET})
    public String testGet(HttpServletRequest request) {
        return JSON.toJSONString(request.getSession().getAttribute("test"));
    }
}
