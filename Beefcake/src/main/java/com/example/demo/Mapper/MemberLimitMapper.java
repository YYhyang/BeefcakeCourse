package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MemberLimitMapper {
    public int getMax();
    public int getMin();
}
