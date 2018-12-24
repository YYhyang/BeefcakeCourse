package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TeamValidApplicationMapper {
    public void createRequest(@Param("teamId") Long teamId, @Param("teacherId") Long teacherId, @Param("reason") String reason);

    public void changeApplicationStatus(@Param("teamId") Long teamId, @Param("status") int status);
}
