package com.qf.service;

import com.qf.entity.Address;
import com.qf.entity.User;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 10:53
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IAddressService {

    List<Address> getAddressList(User user);

    int insertAddress(Address address,int uid);

    Address getAddressById(int addr_id);
}
