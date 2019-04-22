package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.AddressMapper;
import com.qf.entity.Address;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 11:07
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Reference
    private IUserService userService;

    @Override
    public List<Address> getAddressList(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", user.getId());
        return addressMapper.selectList(queryWrapper);
    }

    @Override
    public int insertAddress(Address address, int uid) {
        User user = userService.getUserById(uid);
        List<Address> addressList = this.getAddressList(user);
        System.out.println("是否默认：" + address.getIsdefault());
        if (address.getIsdefault() == 1) {
            for (Address addr : addressList) {
                if (addr.getIsdefault() == 1) {
                    addr.setIsdefault(0);
                    addressMapper.updateById(addr);
                    break;
                }
            }
        }
        address.setUid(user.getId());
        addressMapper.insert(address);
        return 1;
    }

    @Override
    public Address getAddressById(int addr_id) {
        return addressMapper.selectById(addr_id);
    }
}
