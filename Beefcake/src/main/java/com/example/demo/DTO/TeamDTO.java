package com.example.demo.DTO;

import com.example.demo.VO.StudentInTeamVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class TeamDTO {
    String name;
    String courseId;
    String classId;
    Map<String,String> leader;
    List<Map<String,String>> members;
}
