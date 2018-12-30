package com.example.demo.mapper;

import com.example.demo.strategy.ConflictCourseStrategy;
import com.example.demo.strategy.CourseMemberLimitStrategy;
import com.example.demo.strategy.MemberLimitStrategy;
import com.example.demo.strategy.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface StrategyMapper {

    Long getMaxConflictId();

    boolean addConflictCourseStrategy(ConflictCourseStrategy record);

    boolean addMemberLimitStrategy(MemberLimitStrategy record);

    MemberLimitStrategy selectMemberLimitStrategyById(Long id);

    CourseMemberLimitStrategy selectCourseMemberLimitStrategyById(Long id);

    List<ConflictCourseStrategy> selectConflictCourseStrategyById(Long id);



}
