package com.kong.factory.abstract_;

public interface IProductFactory {
    // 生产手机
    IphoneProduct iphoneProduct();

    // 生产路由器
    IRouterProduct irouterProduct();
}
