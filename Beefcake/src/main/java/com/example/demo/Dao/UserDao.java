package com.example.demo.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select({"SELECT role FROM user WHERE account= #{account} AND password= #{password}"})
    public int getRole(@Param("account") String account,@Param("password") String password);

    @Select({"SELECT status FROM user WHERE account= #{account} AND password= #{password}"})
    public int getStatus(@Param("account") String account,@Param("password") String password);

}
