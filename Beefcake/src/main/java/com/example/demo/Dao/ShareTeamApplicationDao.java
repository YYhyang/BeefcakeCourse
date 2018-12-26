package com.example.demo.Dao;

import com.example.demo.Entity.ShareApplicationEntity;
import com.example.demo.Mapper.ShareTeamApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShareTeamApplicationDao {
    @Autowired
    ShareTeamApplicationMapper shareTeamApplicationMapper;

    public void setStatusNull(Long mainCourseId,Long subCourseId){
        shareTeamApplicationMapper.setStatusNull(mainCourseId,subCourseId);
    }

    public String getId(Long mainCourseId,Long subCourseId){
        return shareTeamApplicationMapper.getId(mainCourseId,subCourseId);
    }

    public ShareApplicationEntity getShareTeamApplication(Long mainCourseId, Long subCourseId){
        return  shareTeamApplicationMapper.getShareTeamApplication(mainCourseId,subCourseId);
    }

    public ShareApplicationEntity getShareById(Long Id){
        return shareTeamApplicationMapper.getShareById(Id);
    }

    public void createShareTeamApplication(Long mainCourseId,Long subCourseId,Long subCourseTeacherId){
        shareTeamApplicationMapper.createShareTeamApplication(mainCourseId,subCourseId,subCourseTeacherId);
    }

    public List<Long> getTeamSharesId(Long courseId){
        return shareTeamApplicationMapper.getTeamSharesId(courseId);
    }

    public void deleteTeamShare(Long Id){
        shareTeamApplicationMapper.deleteTeamShare(Id);
    }
}
