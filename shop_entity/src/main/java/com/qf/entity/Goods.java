package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/9 10:26
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods")
public class Goods implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;

    private String gname;

    private BigDecimal gprice;

    private int gsave;

    private String ginfo;

    private String gimage;

    private int status;

    private Date createtime = new Date();

    private int tid;
}
