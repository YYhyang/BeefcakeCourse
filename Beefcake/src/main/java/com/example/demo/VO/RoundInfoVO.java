package com.example.demo.VO;

public class RoundInfoVO {
    private Long id;
    private int order;
    private int presentation_score_method;
    private int report_score_method;
    private int question_score_method;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
    public int getPresentation_score_method() { return presentation_score_method; }
    public void setPresentation_score_method(int presentation_score_method) { this.presentation_score_method = presentation_score_method; }
    public int getReport_score_method() { return report_score_method; }
    public void setReport_score_method(int report_score_method) { this.report_score_method = report_score_method; }
    public int getQuestion_score_method() { return question_score_method; }
    public void setQuestion_score_method(int question_score_method) { this.question_score_method = question_score_method; }
}
