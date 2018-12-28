package com.example.demo.dao;

import com.example.demo.entity.ShareApplicationEntity;
import com.example.demo.mapper.ShareTeamApplicationMapper;
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

    public ShareApplicationEntity getShareTeamById(Long shareId){
        return shareTeamApplicationMapper.getShareTeamById(shareId);
    }

    public List<ShareApplicationEntity> getShareTeamRequest(Long subTeacherId){
        return shareTeamApplicationMapper.getShareTeamRequest(subTeacherId);
    }

    public ShareApplicationEntity getShareById(Long id){
        return shareTeamApplicationMapper.getShareById(id);
    }

    public void createShareTeamApplication(Long mainCourseId,Long subCourseId,Long subCourseTeacherId){
        shareTeamApplicationMapper.createShareTeamApplication(mainCourseId,subCourseId,subCourseTeacherId);
    }

    public List<Long> getTeamSharesId(Long courseId){
        return shareTeamApplicationMapper.getTeamSharesId(courseId);
    }

    public void deleteTeamShare(Long id){
        shareTeamApplicationMapper.deleteTeamShare(id);
    }

    public void setStatus(Long shareId,int status){
        shareTeamApplicationMapper.setStatus(shareId,status);
    }
}
