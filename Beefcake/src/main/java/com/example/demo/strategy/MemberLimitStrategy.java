package com.example.demo.strategy;

import com.example.demo.Entity.StudentEntity;
import com.example.demo.Entity.TeamEntity;
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
        int count=0;
        List<StudentEntity> members=team.getMembers();
        for(StudentEntity stu:members)
            count++;
        if(min_member<=count&&count<=max_member)
            return true;
        else
            return false;
    }
}
