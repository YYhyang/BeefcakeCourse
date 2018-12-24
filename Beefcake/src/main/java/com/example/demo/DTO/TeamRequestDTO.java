package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;


public class TeamRequestDTO {
    private String requestType;
    private Long courseId;
    private Long classId;
    private Long teamId;
    private Long leaderId;
    private String reason;

    public String getRequestType() { return requestType; }
    public void setRequestType(String requestType) { this.requestType = requestType; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
    public Long getLeaderId() { return leaderId; }
    public void setLeaderId(Long leaderId) { this.leaderId = leaderId; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
