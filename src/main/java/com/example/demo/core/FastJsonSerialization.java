package com.example.demo.core;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @author felix.ou
 */
public class FastJsonSerialization {

    public byte[] serialize(Object obj) throws IOException {
        if (obj == null) {
            throw new IOException();
        }
        return JSON.toJSONBytes(obj);
    }

    public Object deserialize(byte[] by) throws IOException {
        if (by == null) {
            throw new IOException();
        }
        return JSON.parse(by);
    }

}
