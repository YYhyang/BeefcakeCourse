package com.example.demo.dao;

import com.example.demo.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceDao {
    @Autowired
    private AttendanceMapper attendanceMapper;
    public boolean postReport(Long attendanceId,String reportName,String reportUrl){return attendanceMapper.postReport(attendanceId,reportName,reportUrl);}
}
