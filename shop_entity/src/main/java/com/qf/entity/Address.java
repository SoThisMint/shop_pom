package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 10:36
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    @TableId(type= IdType.AUTO)
    private int id;
    private String address;
    private int uid;
    private String uname;
    private String phone;
    private String code;
    private int isdefault;
}
