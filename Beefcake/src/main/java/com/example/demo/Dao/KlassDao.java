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

    public List<Long> getAllKlassId(Long klassId){
        return klassMapper.getAllKlassId(klassId);
    }
}
