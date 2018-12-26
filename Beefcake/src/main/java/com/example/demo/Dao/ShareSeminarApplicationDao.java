package com.example.demo.Dao;

import com.example.demo.Entity.ShareApplicationEntity;
import com.example.demo.Mapper.ShareSeminarApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShareSeminarApplicationDao {
    @Autowired
    ShareSeminarApplicationMapper shareSeminarApplicationMapper;

    public void setStatusNull(Long mainCourseId,Long subCourseId){
        shareSeminarApplicationMapper.setStatusNull(mainCourseId,subCourseId);
    }

    public String getId(Long mainCourseId,Long subCourseId){
        return shareSeminarApplicationMapper.getId(mainCourseId,subCourseId);
    }

    public ShareApplicationEntity getShareSeminarApplication(Long mainCourseId, Long subCourseId){
        return  shareSeminarApplicationMapper.getShareSeminarApplication(mainCourseId,subCourseId);
    }

    public ShareApplicationEntity getShareById(Long Id){
        return shareSeminarApplicationMapper.getShareById(Id);
    }

    public void createShareSeminarApplication(Long mainCourseId,Long subCourseId,Long subCourseTeacherId){
        shareSeminarApplicationMapper.createShareSeminarApplication(mainCourseId,subCourseId,subCourseTeacherId);
    }


    public List<Long> getSeminarSharesId(Long courseId){
        return shareSeminarApplicationMapper.getSeminarSharesId(courseId);
    }

    public void deleteSeminarShare(Long Id){
        shareSeminarApplicationMapper.deleteSeminarShare(Id);
    }
}
