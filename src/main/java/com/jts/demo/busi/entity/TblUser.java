package com.jts.demo.busi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("TBL_USER")
public class TblUser {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
