package com.sample;


public class Course {

  private long id;
  private String name;
  private String introduction;
  private double presentationPercent;
  private double questionPercent;
  private double reportPercent;
  private String teacherId;
  private long isMain;
  private long roundNo;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }


  public double getPresentationPercent() {
    return presentationPercent;
  }

  public void setPresentationPercent(double presentationPercent) {
    this.presentationPercent = presentationPercent;
  }


  public double getQuestionPercent() {
    return questionPercent;
  }

  public void setQuestionPercent(double questionPercent) {
    this.questionPercent = questionPercent;
  }


  public double getReportPercent() {
    return reportPercent;
  }

  public void setReportPercent(double reportPercent) {
    this.reportPercent = reportPercent;
  }


  public String getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
  }


  public long getIsMain() {
    return isMain;
  }

  public void setIsMain(long isMain) {
    this.isMain = isMain;
  }


  public long getRoundNo() {
    return roundNo;
  }

  public void setRoundNo(long roundNo) {
    this.roundNo = roundNo;
  }

}
