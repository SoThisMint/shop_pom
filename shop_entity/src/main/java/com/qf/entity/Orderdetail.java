package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/20 17:04
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderdetail implements Serializable {

    @TableId(type= IdType.AUTO)
    private int id;
    private int gid;
    private String oid;
    private String gname;
    private String gimage;
    private BigDecimal gprice;
    private int gnumber;
}
