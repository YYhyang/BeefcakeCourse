package com.example.demo.strategy;

import com.example.demo.Entity.TeamEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseMemberLimitStrategy {
    private Long id;
    private Long course_id;
    private int min_member;
    private int max_member;

 public boolean isValid(int size)
 {
     if(this.max_member==0)
     {
         if(size>=min_member)
             return true;
         else
             return false;
     }
     else
     {
         if(min_member<=size&&size>=max_member)
             return true;
         else return false;
     }
 }

}
