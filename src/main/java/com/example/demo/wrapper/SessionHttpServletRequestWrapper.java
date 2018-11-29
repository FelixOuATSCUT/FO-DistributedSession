package com.example.demo.wrapper;

import com.example.demo.core.DispatcherSession;
import com.example.demo.utils.CookieUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author felix.ou
 */
public class SessionHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public SessionHttpServletRequestWrapper(HttpServletRequest request, HttpServletResponse response) {
        super(request);
        this.request = request;
        this.response = response;
    }

    @Override
    public HttpSession getSession() {
        return this.getSession(true);
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (create) {
            String id = CookieUtil.getCookieValue(request, "sessionId");
            if (StringUtils.isEmpty(id)) {
                id = UUID.randomUUID().toString();
                CookieUtil.setCookie(request, response, "sessionId", id);
            }
            this.session = new DispatcherSession(this.request, this.response, id);
            return this.session;
        } else {
            return null;
        }
    }
}
