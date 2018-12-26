package com.example.demo.Mapper;

import com.example.demo.Entity.ShareApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShareSeminarApplicationMapper {
    public ShareApplicationEntity getShareSeminarApplication(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    public ShareApplicationEntity getShareById(@Param("Id") Long Id);

    public void createShareSeminarApplication(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId, @Param("subCourseTeacherId") Long subCourseTeacherId);

    public void setStatusNull(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    public String getId(@Param("mainCourseId") Long mainCourseId, @Param("subCourseId") Long subCourseId);

    public List<Long> getSeminarSharesId(@Param("courseId") Long courseId);

    public void deleteSeminarShare(@Param("Id") Long Id);
}
