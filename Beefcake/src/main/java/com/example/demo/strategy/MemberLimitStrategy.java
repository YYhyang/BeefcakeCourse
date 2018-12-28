package com.example.demo.strategy;

import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.TeamEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MemberLimitStrategy {
    private Long id;
    private int min_member;
    private int max_member;

    public boolean isValid(TeamEntity team)
    {
        //组长也要算
        int count=1;
        List<StudentEntity> members=team.getMembers();
        for(StudentEntity stu:members) {
            count++;
        }
        if(min_member<=count&&count<=max_member) {
            return true;
        } else {
            return false;
        }
    }
}
