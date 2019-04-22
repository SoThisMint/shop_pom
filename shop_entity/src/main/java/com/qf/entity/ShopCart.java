package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/18 9:06
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("shopcart")
public class ShopCart implements Serializable {

    @TableId(type= IdType.AUTO)
    private int id;
    private int gid;
    private int uid;
    //商品小计
    private BigDecimal allprice;
    //商品数量
    private int gnumber;
    @TableField(exist = false)
    private Goods goods;
}
