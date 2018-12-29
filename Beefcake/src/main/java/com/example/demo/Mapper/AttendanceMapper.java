package com.example.demo.mapper;

import com.example.demo.entity.AttendanceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface AttendanceMapper {

    /**
     * 报名讨论课
     *
     * @param klassSeminarId
     * @param presentationOrder
     * @param teamId
     * @return
     */
    boolean signUp(@Param("klassSeminarId") Long klassSeminarId, @Param("presentationOrder") int presentationOrder, @Param("teamId") Long teamId);

    /**
     * 修改报名顺序
     *
     * @param attendanceId
     * @param presentationOrder
     * @return
     */
    boolean changeOrder(@Param("attendanceId") Long attendanceId, @Param("presentationOrder") int presentationOrder);

    /**
     * 上传报告
     *
     * @param attendanceId
     * @param reportName
     * @param reportUrl
     * @return
     */
    boolean postReport(@Param("attendanceId") Long attendanceId, @Param("report_name") String reportName, @Param("report_url") String reportUrl);

    /**
     * 取消报名
     *
     * @param attendanceId
     * @return
     */
    boolean deleteSignUp(@Param("attendanceId") Long attendanceId);

    boolean setStatus(@Param("attendanceId") Long attendanceId, @Param("status") int status);

    /**
     * 通过seminarId和classId获取klass_seminar_Id
     *
     * @param seminarId
     * @param classId
     * @return
     */
    Long findKlassSeminarId(@Param("seminarId") Long seminarId, @Param("classId") Long classId);

    AttendanceEntity getAttendanceById(@Param("attendanceId") Long attendanceId);

    /**
     * 查找这节课所有报名的小组信息
     *
     * @param klassSeminarId
     * @return
     */
    List<AttendanceEntity> findAllTeam(@Param("klassSeminarId") Long klassSeminarId);



    //**************未调用 待处理*******************

    Long getCourseBySeminarId(@Param("seminarId") Long seminarId);

    boolean startPresentation(@Param("attendanceId") Long attendanceId);

}
