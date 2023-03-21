package com.kong.factory.abstract_;

//路由器产品接口
public interface IRouterProduct {
    void start();
    void shutdown();
    void openWifi();
    void setting();
}
