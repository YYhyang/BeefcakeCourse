package com.example.demo.VO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KlassSeminarInfo {
    private Long klassSeminarId;//唯一表示id
    private int round;//第几轮
    private String seminarName;//名字
    private String introduction;//简介
    private int seminarSerial;//本轮第几次
    private int status;//0未开始1正在进行2已经结束

}
