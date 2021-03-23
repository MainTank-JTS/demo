package com.jts.demo.busi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jts.demo.busi.entity.TblUser;
import org.apache.ibatis.annotations.Select;

public interface TblUserDao extends BaseMapper<TblUser> {
    @Select("SELECT * FROM TBL_USER WHERE ID = #{id}")
    TblUser getById(int id);
}
