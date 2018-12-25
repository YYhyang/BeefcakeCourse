package com.example.demo.Dao;

import com.example.demo.Mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceDao {
    @Autowired
    private AttendanceMapper attendanceMapper;

    public boolean postReport(Long attendanceId,String report_name,String report_url){return attendanceMapper.postReport(attendanceId,report_name,report_url);}
}