package com.example.demo.Dao;

import com.example.demo.Entity.ClassEntity;
import com.example.demo.Mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlassDao {
    @Autowired
    private KlassMapper klassMapper;

    public ClassEntity getKlassById(Long klassId){
        return klassMapper.getKlassById(klassId);
    }

    public List<Long> getAllKlassId(Long courseId){
        return klassMapper.getAllKlassId(courseId);
    }

    public void createKlass(Long courseId,int grade,int klassSerial,String klassTime,String klassLocation){
        klassMapper.createKlass(courseId,grade,klassSerial,klassTime,klassLocation);
    }

    public Long getKlassId(Long courseId,int grade,int klassSerial){
        return klassMapper.getKlassId(courseId,grade,klassSerial);
    }
}
