package com.example.demo.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author felix.ou
 */
public class DispatcherSession implements HttpSession {

    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> sessionMap = null;
    private SessionRedisHelper sessionHelper = new SessionRedisHelper();
    private String sid;

    public DispatcherSession() {
    }

    public DispatcherSession(HttpSession session) {
        this.session = session;
    }

    public DispatcherSession(HttpServletRequest request, HttpServletResponse response, String id) {
        this.sid = id;
        this.request = request;
        this.response = response;
    }

    @Override
    public long getCreationTime() {
        return System.currentTimeMillis();
    }

    @Override
    public String getId() {
        return this.sid;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String name) {
        if (sessionMap == null) {
            sessionMap = sessionHelper.getSession(this.getId());
        }
        return sessionMap.get(name);
    }

    @Override
    public Object getValue(String name) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (sessionMap == null) {
            sessionMap = sessionHelper.getSession(this.getId());
        }
        this.sessionMap.put(name, value);
        sessionHelper.saveSession(this.getId(), sessionMap);
    }

    @Override
    public void putValue(String name, Object value) {

    }

    @Override
    public void removeAttribute(String name) {
        if (sessionMap == null) {
            sessionMap = sessionHelper.getSession(this.getId());
        }
        sessionMap.remove(name);
        sessionHelper.removeSession(this.getId());
    }

    @Override
    public void removeValue(String name) {

    }

    @Override
    public void invalidate() {
        this.sessionMap.clear();
        sessionHelper.removeSession(this.getId());

    }

    @Override
    public boolean isNew() {
        return false;
    }
}