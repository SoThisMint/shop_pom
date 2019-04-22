package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 16:59
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;
    private String oid;
    private int uid;
    private String address;
    private String phone;
    private String code;
    private BigDecimal allprice;
    private Date createtime;
    private int status;

    @TableField(exist = false)
    private List<Orderdetail> orderdetails;
}
