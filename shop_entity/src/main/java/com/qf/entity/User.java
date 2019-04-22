package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/17 17:26
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private int status = 0;
}
