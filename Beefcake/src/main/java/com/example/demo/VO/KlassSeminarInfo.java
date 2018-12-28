package com.example.demo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KlassSeminarInfo {
    //唯一表示id
    private Long klassSeminarId;
    //第几轮
    private int round;
    //名字
    private String seminarName;
    //简介
    private String introduction;
    //本轮第几次
    private int seminarSerial;
    //0未开始1正在进行2已经结束
    private int status;

}
