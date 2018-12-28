package com.example.demo.mapper;

import com.example.demo.entity.AttendanceEntity;
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

    //上传报告
    public boolean postReport(@Param("attendanceId")Long attendanceId,@Param("report_name")String reportName,@Param("report_url")String reportUrl);

    //取消报名

    public boolean deleteSignUp(@Param("attendanceId")Long attendanceId);

    public Long getCourseBySeminarId(@Param("seminarId")Long seminarId);

    public boolean setStatus(@Param("attendanceId")Long attendanceId, @Param("status")int status);

    public AttendanceEntity getAttendanceById(@Param("attendanceId")Long attendanceId);

    public boolean startPresentation(@Param("attendanceId")Long attendanceId);

}
