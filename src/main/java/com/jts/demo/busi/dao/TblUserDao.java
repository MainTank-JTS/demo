package com.jts.demo.busi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jts.demo.busi.entity.TblUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TblUserDao extends BaseMapper<TblUser> {
    @Select("SELECT ID,NAME,AGE,EMAIL FROM TBL_USER ")
    List<TblUser> findAll();

    @Select("SELECT ID,NAME,AGE,EMAIL FROM TBL_USER WHERE id = #{id}")
    TblUser getById(int id);

    @Insert("INSERT INTO TBL_USER(ID,NAME,AGE,EMAIL) VALUES(#{id},#{name},#{age},#{email})")
    int insert(TblUser tblUser);
}
