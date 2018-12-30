package com.example.demo.strategy;

import com.example.demo.dto.CourseMemberLimitDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseMemberLimitStrategy extends Strategy {
    private Long course_id;
    private int min_member;
    private int max_member;

 public boolean isValid(int size)
 {
     if(this.max_member==0)
     {
         if(size>=min_member){
             return true;}
         else{
             return false;}
     }
     else
     {
         if(min_member<=size&&size>=max_member)
         {
             return true;
         }
         else {
             return false;
         }
     }
 }

 public CourseMemberLimitStrategy(CourseMemberLimitDTO courseMemberLimitDTO){
     this.min_member=courseMemberLimitDTO.getMinMember();
     this.max_member=courseMemberLimitDTO.getMaxMember();
     this.course_id=courseMemberLimitDTO.getCourseId();
 }

}
