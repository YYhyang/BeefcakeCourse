package com.example.demo.mapper;

import com.example.demo.entity.ShareApplicationEntity;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShareTeamApplicationMapper {

    void createShareTeamApplication(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId, @Param("subCourseTeacherId") Long subCourseTeacherId);

    void setStatus(@Param("shareId") Long shareId, @Param("status") int status);

    void setStatusNull(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    void deleteTeamShare(@Param("Id") Long id);

    /**
     * 无论课程未主课程还是从课程，获得组队分享记录Id
     *
     * @param courseId
     * @return
     */
    List<Long> getTeamSharesId(@Param("courseId") Long courseId);

    /**
     * 按指定的主从课程，获得组队分享记录Id
     *
     * @param mainCourseId
     * @param subCourseId
     * @return
     */
    String getId(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    ShareApplicationEntity getShareTeamApplication(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    ShareApplicationEntity getShareById(@Param("Id") Long id);
    //*****************重复*******************
    ShareApplicationEntity getShareTeamById(@Param("shareId") Long shareId);

    /**
     * 获得从另一课程发回的共享分组请求
     *
     * @param subTeacherId
     * @return List<ShareApplicationEntity>
     */
    List<ShareApplicationEntity> getShareTeamRequest(@Param("subTeacherId") Long subTeacherId);


}
