package com.example.demo.Entity_renew;

public class ClassEntity {
    private Long classId;
    private ClassEntity course;
    private String name;
    private String classroom;
    private String time;
    //还有一个学生名单，还没学怎么存文件，等到时候学会了确定怎么写


    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public void setCourse(ClassEntity course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getClassId() {
        return classId;
    }

    public ClassEntity getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getTime() {
        return time;
    }
}
