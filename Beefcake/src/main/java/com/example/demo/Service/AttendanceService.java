package com.example.demo.service;

import com.example.demo.dao.AttendanceDao;
import com.example.demo.entity.AttendanceEntity;
import com.example.demo.mapper.AttendanceMapper;
import com.example.demo.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    AttendanceDao attendanceDao;
    public List<AttendanceEntity>findAllTeam(Long seminarId, Long classId)
    {
        Long klassSeminarId= attendanceMapper.findKlassSeminarId(seminarId, classId);
        List<AttendanceEntity> attendanceEntities= attendanceMapper.findAllTeam(klassSeminarId);
        attendanceMapper.setStatus(attendanceEntities.get(0).getId(),1);
        return attendanceEntities;
    }

    public FileVO uploadFile(Long attendanceId,MultipartFile file)throws IOException
    {
        if (file.isEmpty()) {
            return new FileVO();
        }
        else{
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "D://AAA//";
            String path = filePath + fileName + suffixName;
            if(postReport(attendanceId,fileName,path)) {
                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
                // 文件写入
                file.transferTo(dest);
                FileVO vo=new FileVO();
                vo.setUrl(path);
                return vo;
            }
            else
            {
                return new FileVO();
            }
        }
    }

    public boolean signUpAttendence(Long seminarId,Long classId,int presentationOrder,Long teamId)
    {
        Long klassSeminarId= attendanceMapper.findKlassSeminarId(seminarId, classId);
        return attendanceMapper.signUp(klassSeminarId,presentationOrder,teamId);
    }

    public boolean postReport(Long attendanceId,String reportName,String reportUrl){return attendanceDao.postReport(attendanceId,reportName,reportUrl);}

    public boolean nextTeam(Long attendanceId1,Long attendanceId2)
    {
        boolean b1=attendanceMapper.setStatus(attendanceId2,1);
        boolean b2=attendanceMapper.setStatus(attendanceId1,2);
        return b1&b2;
    }
}
