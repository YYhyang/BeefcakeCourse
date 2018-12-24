package com.example.demo.VO;

public class RoundScoreInfoVO {
    private double  presentation_score;
    private double report_score;
    private double question_score;
    private double total_score;
    private Long team_id;
    private String team_name;
    private Long round_id;
    private int round_order;

    public double getPresentation_score() { return presentation_score; }
    public void setPresentation_score(double presentation_score) { this.presentation_score = presentation_score; }
    public double getReport_score() { return report_score; }
    public void setReport_score(double report_score) { this.report_score = report_score; }
    public double getQuestion_score() { return question_score; }
    public void setQuestion_score(double question_score) { this.question_score = question_score; }
    public double getTotal_score() { return total_score; }
    public void setTotal_score(double total_score) { this.total_score = total_score; }
    public Long getTeam_id() { return team_id; }
    public void setTeam_id(Long team_id) { this.team_id = team_id; }
    public String getTeam_name() { return team_name; }
    public void setTeam_name(String team_name) { this.team_name = team_name; }
    public Long getRound_id() { return round_id; }
    public void setRound_id(Long round_id) { this.round_id = round_id; }
    public int getRound_order() { return round_order; }
    public void setRound_order(int round_order) { this.round_order = round_order; }
}
