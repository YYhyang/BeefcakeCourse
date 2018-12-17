package com.example.demo.Mapper;

import com.example.demo.Entity.TeamSeminarEntity;
import com.example.demo.Entity.AttendanceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface AttendanceMapper {

    //通过seminarId和classId获取klass_seminar_Id
    public Long findKlassSeminarId(@Param("seminarId")Long seminarId,@Param("classId")Long classId);

    //查找这节课所有报名的小组信息
    public List<AttendanceEntity> findAllTeam(@Param("klassSeminarId")Long klassSeminarId);

    //报名讨论课
    public boolean signUp(@Param("klassSeminarId")Long klassSeminarId,@Param("presentationOrder")int presentationOrder,@Param("teamId")Long teamId);

    //修改报名顺序

    public boolean changeOrder(@Param("attendanceId")Long attendanceId,@Param("presentationOrder")int presentationOrder);

    //取消报名

    public boolean deleteSignUp(@Param("attendanceId")Long attendanceId);

}
