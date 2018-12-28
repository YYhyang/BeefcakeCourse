package com.example.demo.dao;

import com.example.demo.mapper.MemberLimitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberLimitDao {
    @Autowired
    private MemberLimitMapper memberLimitMapper;

    public int getMax(){
        return memberLimitMapper.getMax();
    }

    public int getMin(){
        return memberLimitMapper.getMin();
    }
}
