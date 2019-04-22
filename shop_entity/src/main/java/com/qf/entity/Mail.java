package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/18 17:05
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {

    private int id;
    private String from;
    private String to;
    private String title;
    private String content;
    private Date createtime;
}
