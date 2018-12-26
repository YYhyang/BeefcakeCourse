package com.example.demo.Service;

import com.example.demo.Dao.AttendanceDao;
import com.example.demo.Entity.AttendanceEntity;
import com.example.demo.Mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public boolean signUpAttendence(Long seminarId,Long classId,int presentationOrder,Long teamId)
    {
        Long klassSeminarId= attendanceMapper.findKlassSeminarId(seminarId, classId);
        return attendanceMapper.signUp(klassSeminarId,presentationOrder,teamId);
    }

    public boolean postReport(Long attendanceId,String report_name,String report_url){return attendanceDao.postReport(attendanceId,report_name,report_url);}

    public void nextTeam(Long attendanceId1,Long attendanceId2)
    {
        attendanceMapper.setStatus(attendanceId2,1);
        attendanceMapper.setStatus(attendanceId1,2);
    }
}
