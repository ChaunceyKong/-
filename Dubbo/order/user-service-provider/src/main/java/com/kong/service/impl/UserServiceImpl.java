package com.kong.service.impl;

import com.kong.pojo.UserAddress;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {
    public List<UserAddress> getUserAddressList(String userId) {

        UserAddress address1 = new UserAddress(1, "上海市徐汇区龙华西路", "1", "熊大", "15012341234", "Y");
        UserAddress address2 = new UserAddress(2, "北京市昌平区沙河镇沙阳路", "1", "熊二", "17666666666", "N");

        return Arrays.asList(address1,address2);
    }
}
