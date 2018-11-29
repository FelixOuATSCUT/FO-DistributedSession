package com.example.demo.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author felix.ou
 */
public class CookieUtil {

    /**
     * 默认保存时间为1小时
     */
    private static final int DEFAULT_SECONDS = 3600;

    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String value) {

        setCookie(request, response, name, value, DEFAULT_SECONDS);
    }

    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String value, int seconds) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value))
            return;
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(seconds);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equalsIgnoreCase(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return "";
    }
}