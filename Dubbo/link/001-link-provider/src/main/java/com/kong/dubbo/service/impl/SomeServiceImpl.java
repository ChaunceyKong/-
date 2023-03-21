package com.kong.dubbo.service.impl;

import com.kong.dubbo.service.SomeService;

public class SomeServiceImpl implements SomeService {
    public String hello(String msg) {
        // 调用数据持久层
        return "hello" + msg;
    }
}
