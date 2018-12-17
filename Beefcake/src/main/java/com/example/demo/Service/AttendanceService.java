package com.example.demo.Service;

import com.example.demo.Entity_renew.AttendanceEntity;
import com.example.demo.Mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;
    public List<AttendanceEntity>findAllTeam(Long seminarId, Long classId)
    {
        Long klassSeminarId= attendanceMapper.findKlassSeminarId(seminarId, classId);
        return attendanceMapper.findAllTeam(klassSeminarId);
    }

    public boolean signUpAttendence(Long seminarId,Long classId,int presentationOrder,Long teamId)
    {
        Long klassSeminarId= attendanceMapper.findKlassSeminarId(seminarId, classId);
        return attendanceMapper.signUp(klassSeminarId,presentationOrder,teamId);
    }

}
