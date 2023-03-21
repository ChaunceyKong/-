package com.kong.factory.abstract_;

public class HuaweiFactory implements IProductFactory{
    @Override
    public IphoneProduct iphoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public IRouterProduct irouterProduct() {
        return new HuaweiRouter();
    }
}
