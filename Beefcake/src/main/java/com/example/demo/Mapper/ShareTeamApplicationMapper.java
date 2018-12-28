package com.example.demo.mapper;

import com.example.demo.entity.ShareApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShareTeamApplicationMapper {
    public ShareApplicationEntity getShareTeamApplication(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    public List<ShareApplicationEntity> getShareTeamRequest(@Param("subTeacherId") Long subTeacherId);

    public ShareApplicationEntity getShareById(@Param("Id") Long id);

    public void createShareTeamApplication(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId, @Param("subCourseTeacherId") Long subCourseTeacherId);

    public void setStatusNull(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    public String getId(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    public List<Long> getTeamSharesId(@Param("courseId") Long courseId);

    public void deleteTeamShare(@Param("Id") Long id);

    public void setStatus(@Param("shareId") Long shareId, @Param("status") int status);

    public ShareApplicationEntity getShareTeamById(@Param("shareId") Long shareId);
}
